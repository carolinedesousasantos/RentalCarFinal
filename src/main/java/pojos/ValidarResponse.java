package pojos;

import servlets.SessionServlet;

public class ValidarResponse {

    private boolean erro; 
    private String msg;
    //private SessionServlet session;
    private String statusAluguel;

    public ValidarResponse() {
    }

    public ValidarResponse(boolean erro, String msg, String statusAluguel) {
        this.erro = erro;
        this.msg = msg;
       // this.session=session;
        this.statusAluguel=statusAluguel;
    }

    public boolean isErro() {
        return erro;
    }

    public String getMsg() {
        return msg;
    }


    public String getStatusAluguel() {
        return statusAluguel;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatusAluguel(String statusAluguel) {
        this.statusAluguel = statusAluguel;
    }

    @Override
    public String toString() {
        return "ValidarResponse{" + "erro=" + erro + ", msg=" + msg + ", status=" + statusAluguel + '}';
    }

    
    
}
