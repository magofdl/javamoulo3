
package com.datos;

import com.utilidades.leerProperties;
import java.io.IOException;
import java.sql.*;


public class MysqlConnect
{

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    private Connection connection;
            
    public void efectuarConexionDB() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
          String usuarioDB=leerProperties.leerArchivoPropiedades("usuario.basedatos");
          String claveUsuarioDB=leerProperties.leerArchivoPropiedades("clave.basedatos");
          String urlConexion=leerProperties.leerArchivoPropiedades("url.basedatos");
          String nombreDB=leerProperties.leerArchivoPropiedades("nombre.basedatos");
          String driverDB=leerProperties.leerArchivoPropiedades("driver.basedatos");
          //Cargar el driver de la base de datos.
          Class.forName(driverDB).newInstance();
          //Establecer conexion con la base de datos
          connection = DriverManager.getConnection(urlConexion+nombreDB,usuarioDB,claveUsuarioDB);          
    }
    
    
    


    public ResultSet ejecutarSpConsulta(String []args,String store_name) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
            int tamanio=args.length;
            String call="{call "+store_name+"(";
            for(int i=0;i<tamanio;i++)
            {
                if(i==tamanio-1)
                {
                    call=call.concat("?)}");
                }
                else
                {
                    call=call.concat("?,");
                }
            }            
            CallableStatement cs = connection.prepareCall(call);
            for(int i=0;i<tamanio;i++)
            {
                cs.setString(i+1, args[i]);
            }
            ResultSet resultado=cs.executeQuery();
            return resultado;
    }


    public void ejecutarSP(String []args, String storeName) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
            int tamanio = args.length;        
            String call = "{ call "+storeName+"(";
            for (int i=0;i<tamanio;i++)
            {
                    if(i==tamanio-1)
                        {
                            call=call.concat("?)}");
                        }
                    else
                        {
                            call=call.concat("?,");
                        }
            }
            CallableStatement stmt = connection.prepareCall(call);
            for (int i=0; i<tamanio; i++)
                {
                    stmt.setString(i+1, args[i]);
                }
            stmt.execute();            
            stmt.close();
      }
    
       public ResultSet ejecutarSpConsultasp(String store_name) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
      {                      
            String call="{call "+store_name+"( )}";            
            CallableStatement cs = connection.prepareCall(call);
            ResultSet resultado=cs.executeQuery();
            return resultado;
      }
}
