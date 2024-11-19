package pw.react.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.exceptions.ResourceNotFoundException;
import pw.react.backend.exceptions.UserValidationException;
import pw.react.backend.services.CompanyService;
import pw.react.backend.services.UserService;
import pw.react.backend.web.CompanyDto;
import pw.react.backend.web.UserDto;

import java.util.Collection;

@RestController
@RequestMapping(path = UserController.USERS_PATH)
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    static final String USERS_PATH = "/users";

    private final UserService userService;
    private final CompanyService companyService;

    public UserController(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @Operation(summary = "Create new users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Users created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(allOf = UserDto.class))}
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Something went wrong"
            )
    })
    @PostMapping(path = "")
    public ResponseEntity<Collection<UserDto>> createUsers(@RequestBody Collection<UserDto> users) {
        try {
            Collection<UserDto> newUsers = userService.batchSave(users.stream().map(UserDto::convertToUser).toList())
                    .stream()
                    .map(UserDto::valueFrom)
                    .toList();

            log.info("Password is not going to be encoded");
            return ResponseEntity.status(HttpStatus.CREATED).body(newUsers);
        } catch (Exception ex) {
            throw new UserValidationException(ex.getMessage(), USERS_PATH);
        }
    }

//    @GetMapping(path = "/{companyId}")
//    public ResponseEntity<CompanyDto> getCompany(@RequestHeader HttpHeaders headers, @PathVariable Long companyId) {
//        logHeaders(headers);
//        CompanyDto result = companyService.getById(companyId)
//                .map(CompanyDto::valueFrom)
//                .orElseThrow(() -> new ResourceNotFoundException(String.format("Company with %d does not exist", companyId)));
//        return ResponseEntity.ok(result);
//    }
}
