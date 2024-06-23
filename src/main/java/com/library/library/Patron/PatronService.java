package com.library.library.Patron;

import com.library.library.Exceptions.UserNotFoundException;
import com.library.library.Patron.DTO.PatronDataDTO;
import com.library.library.Patron.DTO.PatronInputDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatronService implements UserDetailsService {

    private final PatronRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Patron Not Found"));
    }

    public PatronDataDTO getPatron(Integer id) {
        Optional<Patron> p = this.repository.findById(id);
        if (p.isPresent()) {
            return this.convertPatronToPatronDataDTO(p.get());
        }
        throw new UserNotFoundException("User Not Found");
    }

    public List<PatronDataDTO> getAllPatrons() {
        List<Patron> patrons = this.repository.findAll();
        return patrons.stream().map(this::convertPatronToPatronDataDTO).toList();
    }


    // User Updating Viewed Data "All data except Password and Enabled"
    public PatronDataDTO updatePatron(Integer Id, PatronInputDataDTO patron) {
        Optional<Patron> p = this.repository.findById(Id);
        if (p.isPresent()) {
            System.out.print(patron);
            Patron obj = this.convertPatronInputDataDTOToPatron(patron);
            System.out.print(obj);
            obj.setID(Id);
            obj.setEmail(p.get().getEmail());
            obj.setUsername(p.get().getUsername());
            System.out.print(obj.toString());
            obj = this.repository.save(obj);
            return this.convertPatronToPatronDataDTO(obj);
        }
        throw new UserNotFoundException("User Not Found");

    }

    /// DTOs Convertors and Vice Versa
    private PatronDataDTO convertPatronToPatronDataDTO(Patron p) {
        return PatronDataDTO.builder()
                .id(p.getID())
                .username(p.getUsername())
                .email(p.getEmail())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .phoneNumber(p.getPhoneNumber())
                .enabled(p.isEnabled())
                .build();
    }

    private Patron convertPatronInputDataDTOToPatron(PatronInputDataDTO patronInputDataDTO) {
        return Patron.builder()
                .username(patronInputDataDTO.getUsername())
                .email(patronInputDataDTO.getEmail())
                .firstName(patronInputDataDTO.getFirstName())
                .lastName(patronInputDataDTO.getLastName())
                .phoneNumber(patronInputDataDTO.getPhoneNumber())
                .build();
    }

}
