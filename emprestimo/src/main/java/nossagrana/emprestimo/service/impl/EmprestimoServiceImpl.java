package nossagrana.emprestimo.service.impl;

import com.sun.el.stream.Optional;
import nossagrana.emprestimo.dto.AtualizarEmprestimoDTO;
import nossagrana.emprestimo.dto.EmprestimoDTO;
import nossagrana.emprestimo.dto.SolicitarEmprestimoDTO;
import nossagrana.emprestimo.entity.Emprestimo;
import nossagrana.emprestimo.repository.EmprestimoRepository;
import nossagrana.emprestimo.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {
    private EmprestimoRepository emprestimoRepositorio;

    public EmprestimoServiceImpl(EmprestimoRepository emprestimoRepositorio) {
        this.emprestimoRepositorio = emprestimoRepositorio;
    }

    @Override
    public List<EmprestimoDTO> getAll() {
        List<Emprestimo> emprestimosEntity = this.emprestimoRepositorio.findAll();

        return emprestimosEntity.stream()
                .map(EmprestimoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void create(SolicitarEmprestimoDTO solicitarEmprestimoDTO) {
        Emprestimo emprestimo = new Emprestimo(solicitarEmprestimoDTO);

        emprestimoRepositorio.save(emprestimo);
    }

    @Override
    public EmprestimoDTO findById(String id) {
        return new EmprestimoDTO(getEmprestimo(id));
    }

    @Override
    public List<EmprestimoDTO> findByEmail(String email) {
        List<Emprestimo> emprestimos = this.emprestimoRepositorio.findAllByEmail(email);
        List<EmprestimoDTO> retorno;
        try {
            retorno = emprestimos.stream().map(EmprestimoDTO::new).collect(Collectors.toList());
        }
         catch (Exception e) {
            throw e;
         }
        return retorno;
    }


    @Override
    public EmprestimoDTO update(String id, AtualizarEmprestimoDTO atualizarEmprestimoDTO) {
        Emprestimo emprestimo = getEmprestimo(id);

        emprestimo.setMontante(atualizarEmprestimoDTO.getMontante());
        emprestimo.setDataVencimento(atualizarEmprestimoDTO.getDataVencimento().toLocalDate());

       return saveAndGetEmprestimoDTO(emprestimo);
    }

    @Override
    public void delete(String id) {
        Emprestimo emprestimo = getEmprestimo(id);
        emprestimoRepositorio.delete(emprestimo);
    }

    private Emprestimo getEmprestimo(String id) {
        return emprestimoRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private EmprestimoDTO saveAndGetEmprestimoDTO(Emprestimo emprestimo) {
        Emprestimo savedCerveja = emprestimoRepositorio.save(emprestimo);
        return new EmprestimoDTO(savedCerveja);
    }
}
