package haida.houssam.backendexam.controllers;

import haida.houssam.backendexam.dtos.RemboursementDTO;
import haida.houssam.backendexam.services.RemboursementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
public class RemboursementController {

    private final RemboursementService remboursementService;

    public RemboursementController(RemboursementService remboursementService) {
        this.remboursementService = remboursementService;
    }

    @PostMapping
    public ResponseEntity<RemboursementDTO> create(@RequestBody RemboursementDTO dto) {
        return ResponseEntity.ok(remboursementService.createRemboursement(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemboursementDTO> getById(@PathVariable Long id) {
        RemboursementDTO dto = remboursementService.getRemboursementById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<RemboursementDTO>> getAll() {
        return ResponseEntity.ok(remboursementService.getAllRemboursements());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        remboursementService.deleteRemboursement(id);
        return ResponseEntity.noContent().build();
    }
}