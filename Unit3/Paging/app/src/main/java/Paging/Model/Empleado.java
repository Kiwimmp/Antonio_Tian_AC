package Paging.Model;
// Empleado.java
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Empleado implements Serializable {

    @Id
    @Column(name = "emp_no")
    private int empNo;

    @Column(name = "birth_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "first_name")
    private String primerNombre;

    @Column(name = "last_name")
    private String apellido;

    @Column(name = "gender")
    private String genero;

    @Column(name = "hire_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaContratacion;

    // getters y setters

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }
    //Constructor vacio
    public Empleado() {
    }
    
}
