package be.ehb.dactylokd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Letter not found")
public class LetterNotFoundException extends Exception {
}
