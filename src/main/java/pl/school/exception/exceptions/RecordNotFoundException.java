package pl.school.exception.exceptions;

import pl.school.exception.messages.ExceptionMessages;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException(String recordName, Long id){
        super(String.format(ExceptionMessages.RECORD_FOR_PROVIDED_ID_NOT_FOUND.getMessage(),recordName, id));
    }

}
