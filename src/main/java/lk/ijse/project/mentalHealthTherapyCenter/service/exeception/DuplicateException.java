package lk.ijse.project.mentalHealthTherapyCenter.service.exeception;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String message) {
        super(message);
    }
}
