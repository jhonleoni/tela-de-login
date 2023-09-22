/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.core.dao;

import main.core.entity.Usuario;
import main.core.dao.conexao.ConexaoJDBC;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author jhony
 */
public class UsuarioDao {
    
    public void inserir (Usuario usuario) throws SQLException {
        
        String sql = "INSERT INTO USUARIO (NOME, EMAIL, LOGIN, SENHA) VALUES (?,?,?,?)";
        
        PreparedStatement ps = null;
                
        try { 
            ps = ConexaoJDBC.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getLogin());
            ps.setString(4, usuario.getSenha());
            
            ps.execute();
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            ps.close();
        }
    }
        public Usuario buscarUsuario(String login){
            
            String sql = "SELECT ID, NOME, LOGIN, SENHA , EMAIL from usuario where LOGIN = ? and SENHA = ?";
            
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            try{
                ps = ConexaoJDBC.getConexao().prepareStatement(sql);
                
                ps.setString(1, login);
                
                rs = ps.executeQuery(); 
                
                
                if(rs.next()){
                    Usuario usuario = new Usuario();
                    
                    usuario.setId(rs.getLong("ID"));
                    usuario.setNome(rs.getString("NOME"));
                    usuario.setEmail(rs.getString("EMAIL"));
                    usuario.setLogin(rs.getString("LOGIN"));
                    usuario.setSenha(rs.getString("SENHA"));
                    
                    return usuario;
                }
                return null;
            }catch(SQLException e){
                
                e.printStackTrace();
            }
            return null;
        }
        
    }

