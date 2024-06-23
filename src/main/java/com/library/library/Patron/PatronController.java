package com.library.library.Patron;

import com.library.library.Exceptions.UserNotFoundException;
import com.library.library.Patron.DTO.PatronDataDTO;
import com.library.library.Patron.DTO.PatronInputDataDTO;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.RequiredArgsConstructor;

import org.apache.tools.ant.taskdefs.Input;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("private/api/patrons")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;

    @GetMapping("")
    public ResponseEntity<List<PatronDataDTO>> getAllPatrons() {
        try {
            return new ResponseEntity<>(this.patronService.getAllPatrons(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDataDTO> getPatron(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(this.patronService.getPatron(id), HttpStatus.OK);
        } catch (UserNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatron(@Parameter(description = "id") @PathVariable(value = "id") Integer id,
            @RequestBody PatronInputDataDTO inputDataDTO, BindingResult bindingResult) {

        System.out.println(bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().toString());
        }
        try {
            return new ResponseEntity<>(this.patronService.updatePatron(id, inputDataDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@Parameter(description = "id") @PathVariable(value = "id") Integer Id) {
        this.patronService.deletePatron(Id);
        return ResponseEntity.noContent().build();
    }
}
