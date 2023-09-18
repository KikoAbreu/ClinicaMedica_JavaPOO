package principal;

import java.util.Date;

public class Consulta {

    private int id;
    private Medico medico;
    private Paciente paciente;
    private Date dataConsulta;


    public Consulta(Medico medico, Paciente paciente, Date dataConsulta) {
        super();
        this.medico = medico;
        this.paciente = paciente;
        this.dataConsulta = dataConsulta;
    }


    public Consulta() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Medico getMedico() {
        return medico;
    }


    public void setMedico(Medico medico) {
        this.medico = medico;
    }


    public Paciente getPaciente() {
        return paciente;
    }


    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }



    public Date getDataConsulta() {
        return dataConsulta;
    }


    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }


}