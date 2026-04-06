package persistencia;

/**
 * Esta clase centraliza el acceso a los implementadores de la persistencia del Board Game Café.
 */
public class CentralPersistencia
{
    // --- Constantes ---
    public static final String JSON = "JSON";

    // --- Métodos Estáticos ---
    public static IPersistenciaCafe getPersistenciaCafe( String tipoArchivo ) throws Exception // Cambia Exception por tu excepción específica si tienes una
    {
        if ( tipoArchivo.equals( JSON ) )
        {
            return new PersistenciaCafeJson( );
        }
        else
        {
            throw new Exception( "Tipo de archivo inválido: " + tipoArchivo );
        }
    }

    public static IPersistenciaOperaciones getPersistenciaOperaciones( String tipoArchivo ) throws Exception
    {
        if ( tipoArchivo.equals( JSON ) )
        {
            return new PersistenciaOperacionesJson( );
        }
        else
        {
            throw new Exception( "Tipo de archivo inválido: " + tipoArchivo );
        }
    }
}
