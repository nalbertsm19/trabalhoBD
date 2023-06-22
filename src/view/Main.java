package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.AgricultorDAO;
import dao.AreaPlantadaDAO;
import dao.ConnectionFactory;
import entidades.Agricultor;
import entidades.AreaPlantada;

public class Main {

		AgricultorDAO agricultorDAO = new AgricultorDAO();
		AreaPlantadaDAO areaPlantadaDAO = new AreaPlantadaDAO();

    public static void main(String[] args) throws SQLException 
    {
        Scanner scanner = new Scanner(System.in);
        
        int opcao;
        do {
            System.out.println("===== MENU =====");
            System.out.println("1. Cadastrar Agricultor");
            System.out.println("2. Cadastrar �rea Plantada");
            System.out.println("3. Atualizar Agricultor");
            System.out.println("4. Atualizar �rea Plantada");
            System.out.println("5. Excluir Agricultor");
            System.out.println("6. Excluir �rea Plantada");
            System.out.println("7. Buscar Agricultor");
            System.out.println("8. Buscar �rea Plantada");
            System.out.println("9. Listar Agricultores");
            System.out.println("10. Listar �reas Plantadas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma op��o: ");
            opcao = scanner.nextInt();
          
            
            
            switch (opcao) {
                case 1:
                	cadastrarAgricultor();
                    break;
                case 2:
                	cadastrarAreaPlantada();
                case 3:
                    atualizarAgricultor();
                    break;
                case 4:
                	atualizarAreaPlantada();
                case 5:
                    excluirAgricultor();
                    break;
                case 6:
                	excluirAreaPlantada();
                case 7:
                    buscarAgricultor();
                    break;
                case 8:
                	 buscarAreaPlantada();
                case 9:
                    listarAgricultores();
                    break;
                case 10:
                	listarAreasPlantadas();
                	break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Op��o inv�lida. Tente novamente.");
            }

            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }
    
    public static void cadastrarAgricultor() throws SQLException {
    	 
    	    Scanner scanner = new Scanner(System.in);

    	    System.out.println("---- Cadastrando Agricultor ----");

    	    System.out.println("Digite a �rea de servi�o:");
    	    String areaServico = scanner.nextLine();

    	    System.out.println("Qual � o seu nome?");
    	    String nome = scanner.nextLine();

    	    System.out.println("Digite sua data de nascimento:");
    	    String dataNasc = scanner.nextLine();

    	    System.out.println("Digite seu CPF:");
    	    String cpf = scanner.nextLine();
    	    

    	    AgricultorDAO agricultorDAO = new AgricultorDAO();
    	    Agricultor agricultorCAD = new Agricultor(areaServico, nome, dataNasc, cpf);
    	    agricultorDAO.cadastrar(agricultorCAD);
    	    
    	    System.out.println("Cadastro efetuado com sucesso!");
    	
	}
    
    public static void cadastrarAreaPlantada() throws SQLException 
    {
    	
        System.out.println("Cadastrando �rea Plantada");
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Informe o tamanho da area:");
        double tamArea= scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Informe o tipo da area:");
        String tipoArea= scanner.nextLine();
        
        System.out.println("Digite a descri��o da �rea Plantada:");
        String descricaoQualidade = scanner.nextLine();
       
        System.out.println("Informe a especie da semente:");
        String espSemente= scanner.nextLine();
       
        System.out.println("Informe o codigo do agricultor");
        int codAgricultor= scanner.nextInt();
       
        
        AreaPlantadaDAO areaPlantadaDAO = new AreaPlantadaDAO();
        AreaPlantada areaPlantada = new AreaPlantada( tamArea,  tipoArea, descricaoQualidade, espSemente, codAgricultor);
        areaPlantadaDAO.cadastrar(areaPlantada);
        System.out.println("Cadastro efetuado com sucesso!");
    } 
    
    
    
    public static void atualizarAgricultor() throws SQLException {
        System.out.println("Atualizando Agricultor");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o c�digo do Agricultor:");
        int codAgricultor = Integer.parseInt(scanner.nextLine());

        AgricultorDAO agricultorDAO = new AgricultorDAO();
        Agricultor agricultor = agricultorDAO.buscar(codAgricultor);

        if (agricultor != null) {
            System.out.println("Digite a nova �rea de servi�o:");
            String areaServico = scanner.nextLine();

            System.out.println("Digite o novo nome:");
            String nome = scanner.nextLine();

            System.out.println("Digite a nova data de nascimento:");
            String dataNasc = scanner.nextLine();

            System.out.println("Digite o novo CPF:");
            String cpf = scanner.nextLine();

            String sql = "UPDATE agricultor SET areaServico = ?, nome = ?, dataNasc = ?, cpf = ? WHERE codAgricultor = ?";
            try (Connection conexao = ConnectionFactory.getConnection();
                 PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, areaServico);
                ps.setString(2, nome);
                ps.setString(3, dataNasc);
                ps.setString(4, cpf);
                ps.setInt(5, codAgricultor);

                int linhasAfetadas = ps.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Agricultor atualizado com sucesso!");
                } else {
                    System.out.println("Falha ao atualizar o Agricultor.");
                }
            } catch (SQLException e) {
                System.out.println("Ocorreu um erro durante a atualiza��o do Agricultor: " + e.getMessage());
            }
        } else {
            System.out.println("Agricultor n�o encontrado.");
        }

        scanner.close();

    }
    
    public static void atualizarAreaPlantada() throws SQLException {
        System.out.println("Atualizando �rea Plantada");
        
         Scanner scanner= new Scanner(System.in);
         
         System.out.println("Informe o tamanho da �rea:");
         double tamArea = scanner.nextDouble();
         scanner.nextLine();

         System.out.println("Informe o tipo da �rea:");
         String tipoArea = scanner.nextLine();

         System.out.println("Digite a descri��o da �rea Plantada:");
         String descricaoQualidade = scanner.nextLine();

         System.out.println("Informe a esp�cie da semente:");
         String espSemente = scanner.nextLine();

         System.out.println("Informe o c�digo do agricultor:");
         int codAgricultor = scanner.nextInt();
         scanner.nextLine();

         String sql = "UPDATE AreaPLnatada SET tamArea = ?, tipoArea = ?, descricaoQualidade = ?, espSemente = ? WHERE codAgricultor = ?";

         try (Connection conexao = ConnectionFactory.getConnection();
              PreparedStatement ps = conexao.prepareStatement(sql)) {
             ps.setDouble(1, tamArea);
             ps.setString(2, tipoArea);
             ps.setString(3, descricaoQualidade);
             ps.setString(4, espSemente);
             ps.setInt(5, codAgricultor);

             int linhasAfetadas = ps.executeUpdate();

             if (linhasAfetadas > 0) {
                 System.out.println("�rea Plantada atualizada com sucesso!");
             } else {
                 System.out.println("Falha ao atualizar a �rea Plantada.");
             }
         } catch (SQLException e) {
             System.out.println("Ocorreu um erro durante a atualiza��o da �rea Plantada: " + e.getMessage());
         }
       

     
        

    }

    
    public static void excluirAgricultor() throws SQLException {
        System.out.println("Excluindo Agricultor");
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite o c�digo do Agricultor:");
        int codAgricultor = Integer.parseInt(scanner.nextLine());
        
        AgricultorDAO agricultorDAO = new AgricultorDAO();
        boolean excluido = agricultorDAO.excluir(codAgricultor);
        
        if (excluido) {
            System.out.println("Agricultor exclu�do com sucesso!");
        } else {
            System.out.println("Falha ao excluir o Agricultor.");
        }
        
        scanner.close();
    }
    
    
    public static void excluirAreaPlantada() throws SQLException {
        System.out.println("Excluindo �rea Plantada");
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite o c�digo da �rea Plantada:");
        int codAreaPlantada = Integer.parseInt(scanner.nextLine());
        
        AreaPlantadaDAO areaPlantadaDAO = new AreaPlantadaDAO();
        boolean excluido = areaPlantadaDAO.excluirAreaPlantada(codAreaPlantada);
        
        if (excluido) {
            System.out.println("�rea Plantada exclu�da com sucesso!");
        } else {
            System.out.println("Falha ao excluir a �rea Plantada.");
        }
        
        scanner.close();
    }
    
    public static void buscarAgricultor() throws SQLException {
        System.out.println("Buscando Agricultor");
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite o c�digo do Agricultor:");
        int codAgricultor = Integer.parseInt(scanner.nextLine());
        
        AgricultorDAO agricultorDAO = new AgricultorDAO();
        Agricultor agricultor = agricultorDAO.buscar(codAgricultor);
        
        if (agricultor != null) {
            System.out.println("Agricultor encontrado:");
            System.out.println(agricultor);
        } else {
            System.out.println("Agricultor n�o encontrado.");
        }
        
        scanner.close();
    }
    public static void buscarAreaPlantada() throws SQLException 
    {
        System.out.println("Buscando �rea Plantada");
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite o c�digo da �rea Plantada:");
        int codAreaPlantada = Integer.parseInt(scanner.nextLine());
        
        AreaPlantadaDAO areaPlantadaDAO = new AreaPlantadaDAO();
        AreaPlantada areaPlantada = areaPlantadaDAO.buscarPorCodigo(codAreaPlantada);
        
        if(areaPlantada != null) {
			System.out.println("Codigo da Area: " + areaPlantada.getCodArea());
			System.out.println("Descri��o: " + areaPlantada.getDescricaoQualidade());
			System.out.println("Tipo Area:"+ areaPlantada.getTipoArea());
			System.out.println("Descri��o de qualidade:"+ areaPlantada.getDescricaoQualidade());
			System.out.println("Especie da semente:"+ areaPlantada.getEspSemente());
			System.out.println("C�digo do agricultor:"+ areaPlantada.getCodAgricultor()+"\n");
		}else {
			System.out.println("N�o existe uma Area Plantada com este codigo...");
		}
        
        scanner.close();
    }
    
    public static void listarAgricultores() throws SQLException {
        System.out.println("Listando Agricultores");
        
        AgricultorDAO agricultorDAO = new AgricultorDAO();
        List<Agricultor> agricultores = agricultorDAO.listaAgri();
        
        if (!agricultores.isEmpty()) {
            System.out.println("Agricultores cadastrados:");
            for (Agricultor agricultor : agricultores) 
            {
                System.out.println("C�digo do agricultor:"+agricultor.getCodAgricultor());
                System.out.println("Area de servico:"+agricultor.getAreaServico());
                System.out.println("Nome:"+agricultor.getNome());
                System.out.println("Data de nascimento:"+agricultor.getDataNasc());
                System.out.println("CPF:"+agricultor.getCpf()+"\n");
                
            }
        } else {
            System.out.println("Nenhum Agricultor cadastrado.");
        }
      
    }
    
    

    
    public static void listarAreasPlantadas() throws SQLException 
    {
        System.out.println("Listando �reas Plantadas \n");
        
        AreaPlantadaDAO areaPlantadaDAO = new AreaPlantadaDAO();
        List<AreaPlantada> areasPlantadas = areaPlantadaDAO.listaArea();
        
        if (!areasPlantadas.isEmpty()) {
            System.out.println("Areas cadastradas");
            for (AreaPlantada areaPlantada : areasPlantadas) 
            {
            	System.out.println("Codigo da Area:" + areaPlantada.getCodArea());
        		System.out.println("Tamanho da Area:"+areaPlantada.getTamArea());
        		System.out.println("Tipo da Area:"+ areaPlantada.getTipoArea());
        		System.out.println("Descri��o:"+areaPlantada.getDescricaoQualidade());
        		System.out.println("Especie da semente:"+areaPlantada.getEspSemente());
        		System.out.println("Codigo do Agricultor" + areaPlantada.getCodAgricultor()+"\n");
               
            }
           
        } else {
            System.out.println("Nenhum Area Cadastrada");
        }
        
    }
}
