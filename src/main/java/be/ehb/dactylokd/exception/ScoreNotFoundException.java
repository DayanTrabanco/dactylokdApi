package be.ehb.dactylokd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No score found for this particular step")
public class ScoreNotFoundException extends Exception{
}
