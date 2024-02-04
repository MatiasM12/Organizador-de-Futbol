package organizador.futbol.infrastructure.adapters.in;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import organizador.futbol.application.FieldService;
import organizador.futbol.domain.Field;

import java.util.List;

@RestController
@RequestMapping("/fields")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @GetMapping
    public ResponseEntity<List<Field>> getAllFields() {
        List<Field> fields = fieldService.getAllFields();
        return ResponseEntity.ok(fields);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Field> getFieldById(@PathVariable Long id) {
        Field field = fieldService.getFieldById(id);
        if (field != null) {
            return ResponseEntity.ok(field);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Field> createField(@RequestBody Field field) {
        Field createdField = fieldService.createField(field);
        return ResponseEntity.ok(createdField);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Field> updateField(@PathVariable Long id, @RequestBody Field field) {
        Field updatedField = fieldService.updateField(id, field);
        if (updatedField != null) {
            return ResponseEntity.ok(updatedField);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        fieldService.deleteField(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/user/{id_user}")
    public ResponseEntity<List<Field>> getFieldsByUserId(@PathVariable Long id_user) {
        List<Field> userMatches = fieldService.getFieldsByUserId(id_user);
        return new ResponseEntity<>(userMatches, HttpStatus.OK);
    }
}
