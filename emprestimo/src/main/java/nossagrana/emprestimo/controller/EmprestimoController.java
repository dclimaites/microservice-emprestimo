package nossagrana.emprestimo.controller;

import nossagrana.emprestimo.dto.AtualizarEmprestimoDTO;
import nossagrana.emprestimo.dto.EmprestimoDTO;
import nossagrana.emprestimo.dto.SolicitarEmprestimoDTO;
import nossagrana.emprestimo.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("emprestimos")
public class EmprestimoController {

    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @GetMapping(value = "{emailUsuario}")
    public List<EmprestimoDTO> listarTodos(@PathVariable String emailUsuario) {
        List<EmprestimoDTO> emprestimos = this.service.findByEmail(emailUsuario);
        return emprestimos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void solicitarEmprestimo(@RequestBody SolicitarEmprestimoDTO solicitarEmprestimoDTO) {
        service.create(solicitarEmprestimoDTO);
    }

    @PutMapping("{id}")
    public EmprestimoDTO atualizarEmprestimo(@PathVariable String id, @RequestBody AtualizarEmprestimoDTO atualizarEmprestimoDTO) {
        return service.update(id, atualizarEmprestimoDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
