package src;

public enum Status
{
    success,
    invalid_client_id,
    invalid_task_id;

    @Override
    public String toString()
    {
        switch (this) {
            case success: 
                return "Is success !";
            case invalid_task_id:
                return "the task id is invalid !";
            case invalid_client_id:
                return "the client id is invalid !";
            default:
                return "ERROR Status.toString()";
        }
    }
}