
package sistema;

/**
 *Classe estática para conexão com banco de dados
 * Objeto único de classe estática para acesso aos dados(DAO)
 * @author Fabricio
 */
public class BancoDeDados {
    public static String conectionString = "jdbc:sqlserver://localhost:1433;databaseName=sistema_de_funcionarios";
    public static String user="sa";
    public static String password="root";
}
