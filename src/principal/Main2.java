package principal;
import java.util.Scanner;

import principal.DAO.MedicoDAO;
import principal.DAO.PacienteDAO;
import principal.DAO.ConsultaDAO;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Date;
public class Main2 {
    public static void main(String[] args) {
        // Criar objeto da Classe DAO do médico
        MedicoDAO medicoDAO = new MedicoDAO();
        PacienteDAO pacienteDAO = new PacienteDAO();

        // Criar médico
//        Medico medico1 = new Medico();
//        medico1.setNome("Dra. Raquel Mota");
//        medico1.setEspecialidade("Veterinaria");
//        medicoDAO.criarMedico(medico1);

        //Criar paciente
          Paciente paciente1 = new Paciente();
//        paciente1.setNome("Kilderys Abreu");
//        paciente1.setIdade(33);
//        pacienteDAO.criarPaciente(paciente1);

        // Listar médicos
//        System.out.println("Médicos:");
//        List<Medico> medicos = null;
//        try {
//            medicos = medicoDAO.listarMedicos();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        for (Medico medico : medicos) {
//            System.out.println(medico.getNome() + " - " + medico.getEspecialidade());
//        }

        //Listar pacientes
//        System.out.println("Pacientes:");
//        List<Paciente> pacientes = null;
//        try {
//            pacientes = pacienteDAO.listarPacientes();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (Paciente paciente : pacientes) {
//            System.out.println(paciente.getNome() + " - " + paciente.getIdade());
//        }


        // Criar / Atualizar Paciente
//        paciente1.setNome("Flavio Mota");
//        paciente1.setIdade(33);
//        paciente1.setId(1);
//        pacienteDAO.atualizarPaciente(paciente1);

       // Excluir Paciente
        //pacienteDAO.excluirPaciente(3);


        //medicoDAO.excluirMedico(2);
//		// Criar médico
//        Medico medico1 = new Medico();
//        medico1.setNome("Dr. Roberto Mota");
//        medico1.setEspecialidade("Cardiologista");
//        medico1.setId(1);
//        medicoDAO.atualizarMedico(medico1);


// Consulta
		ConsultaDAO consultaDAO = new ConsultaDAO();
		Consulta consulta = new Consulta();
		System.out.print("ID do Medico: ");
		Scanner scanner =  new Scanner(System.in);
		int medicoId = scanner.nextInt();
		Medico medicoConsulta = medicoDAO.buscarMedico(medicoId);
		if (medicoConsulta != null) {
			consulta.setMedico(medicoConsulta);
			System.out.print("ID do Paciente: ");
			int pacienteId = scanner.nextInt();
			Paciente pacienteConsulta = pacienteDAO.buscarPaciente(pacienteId);
			if (pacienteConsulta != null) {
				consulta.setPaciente(pacienteConsulta);
				System.out.print("Data da Consulta (dd/mm/yyyy): ");
				String dataString = scanner.next();
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date dataConsulta = sdf.parse(dataString);
					consulta.setDataConsulta(dataConsulta);
					consultaDAO.criarConsulta(consulta);
					System.out.println("Consulta cadastrada com sucesso!");
				} catch (ParseException e) {
					System.out.println("Formato de data inválido. Use dd/mm/yyyy.");
				}
			} else {
				System.out.println("Paciente não encontrado.");
			}
		} else {
			System.out.println("Medico não encontrado.");
		}
	}

	//Buscar consulta por ID








}

