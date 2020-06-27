package nossagrana.emprestimo.service;

import com.sun.el.stream.Optional;
import nossagrana.emprestimo.dto.AtualizarEmprestimoDTO;
import nossagrana.emprestimo.dto.EmprestimoDTO;
import nossagrana.emprestimo.dto.SolicitarEmprestimoDTO;

import java.util.List;

public interface EmprestimoService {
    List<EmprestimoDTO> getAll();
    void create(SolicitarEmprestimoDTO solicitarEmprestimoDTO);
    EmprestimoDTO findById(String id);
    List<EmprestimoDTO> findByEmail(String email);
    EmprestimoDTO update(String id, AtualizarEmprestimoDTO atualizarEmprestimoDTO);
    void delete(String id);
}
