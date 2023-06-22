package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Agricultor;
import entidades.AreaPlantada;

public class AreaPlantadaDAO {
	
	public void cadastrar(AreaPlantada areaPlantada) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		
		String sql = "INSERT INTO AreaPlantada (codArea, tamArea, tipoArea, descricaoQualidade, espSemente, codAgricultor) VALUES (? ,?, ?, ?, ?, ?)";
		
		PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, areaPlantada.getCodArea());
		ps.setDouble(2, areaPlantada.getTamArea());
		ps.setString(3, areaPlantada.getTipoArea());
		ps.setString(4, areaPlantada.getDescricaoQualidade());
		ps.setString(5, areaPlantada.getEspSemente());
		ps.setInt(6, areaPlantada.getCodAgricultor());
		
		ps.executeUpdate();
		
		ResultSet result = ps.getGeneratedKeys();
		
		if (result.next())
			areaPlantada.setCodArea(result.getInt(1));
	}
	
	public boolean atualizar(AreaPlantada areaPlantada) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		
		String sql = "UPDATE AreaPlantada SET tam_area = ?, tipo_area = ?, descricao_qualidade = ?, esp_semente = ?, cod_agricultor = ? WHERE cod_area = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setDouble(1, areaPlantada.getTamArea());
		ps.setString(2, areaPlantada.getTipoArea());
		ps.setString(3, areaPlantada.getDescricaoQualidade());
		ps.setString(4, areaPlantada.getEspSemente());
		ps.setInt(5, areaPlantada.getCodAgricultor());
		ps.setInt(6, areaPlantada.getCodArea());
		
		if (ps.executeUpdate() == 1)
			return true;
		
		return false;
	}
	
	public boolean excluirAreaPlantada(int codArea) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		
		String sql = "DELETE FROM AreaPlantada WHERE codArea = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, codArea);
		
		int excluidos = 0;
		
		try {
			excluidos = ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		
		if (excluidos == 1)
		{
			return true;
		}
		return false;
	}	

	
	 public AreaPlantada buscarPorCodigo(int codArea) throws SQLException 
	 {
		Connection conexao = ConnectionFactory.getConnection();
			
			String sql = "select * from AreaPlantada where codArea = ?";
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, codArea );
			
			ResultSet result = ps.executeQuery();
			
			
			
			if( result.next() ) {
				int area = result.getInt("codArea");
				double tam = result.getDouble("tamArea");
				String tipo = result.getString("tipoArea");
				String desc = result.getString("descricaoQualidade");
				String esp= result.getString("espSemente");
				int agrCod= result.getInt("codAgricultor");
				AreaPlantadaDAO apd = new AreaPlantadaDAO();
			    
				
				AreaPlantada ap = apd.buscarPorCodigo(codArea);
				
				return new AreaPlantada(area, tam, tipo, desc, esp, agrCod);
				
			}
			
			   return null;
	        }

	 public List<AreaPlantada> listaArea() throws SQLException {
	        List<AreaPlantada> areasPlantadas = new ArrayList<>();
	        
	        try (Connection conexao = ConnectionFactory.getConnection()) {
	            String sql = "SELECT * FROM AreaPlantada";
	            
	            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
	                try (ResultSet rs = ps.executeQuery()) {
	                    while (rs.next()) {
	                        int id = rs.getInt("codArea");
	                        double tamArea = rs.getDouble("tamArea");
	                        String tipoArea = rs.getString("tipoArea");
	                        String descricaoQualidade = rs.getString("descricaoQualidade");
	                        String espSemente = rs.getString("espSemente");
	                        int codAgricultor = rs.getInt("codAgricultor");
	                        
	                        AreaPlantada areaPlantada = new AreaPlantada(id, tamArea, tipoArea, descricaoQualidade, espSemente, codAgricultor);
	                        areasPlantadas.add(areaPlantada);
	                    }
	                }
	            }
	        }
	        
	        return areasPlantadas;
	    }


	 }
	 

