package nossagrana.emprestimo.service.impl;

import nossagrana.emprestimo.dto.AtualizarEmprestimoDTO;
import nossagrana.emprestimo.dto.SolicitarEmprestimoDTO;
import nossagrana.emprestimo.entity.Emprestimo;
import nossagrana.emprestimo.repository.EmprestimoRepository;
import nossagrana.emprestimo.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {
    private EmprestimoRepository emprestimoRepositorio;

    public EmprestimoServiceImpl(EmprestimoRepository emprestimoRepositorio) {
        this.emprestimoRepositorio = emprestimoRepositorio;
    }

    @Override
    public List<Emprestimo> getAll() {
        return this.emprestimoRepositorio.findAll();


    }

    @Override
    public Emprestimo create(SolicitarEmprestimoDTO solicitarEmprestimoDTO) {
        Emprestimo emprestimo = new Emprestimo(solicitarEmprestimoDTO);
        return emprestimoRepositorio.save(emprestimo);
    }

    @Override
    public Emprestimo findById(String id) {
        return getEmprestimo(id);
    }

    @Override
    public List<Emprestimo> findByEmail(String email) {
        return this.emprestimoRepositorio.findAllByEmail(email);
    }

    @Override
    public Emprestimo update(String id, AtualizarEmprestimoDTO atualizarEmprestimoDTO) {
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

    private Emprestimo saveAndGetEmprestimoDTO(Emprestimo emprestimo) {
       return emprestimoRepositorio.save(emprestimo);
    }
}
