package pl.school.exception.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessages {

    RECORD_FOR_PROVIDED_ID_NOT_FOUND("Rekord %s dla podanego id: %s nie istnieje");

    private final String message;


}
