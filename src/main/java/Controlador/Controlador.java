package Controlador;

import Mapeo.*;
import Modelo.*;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * @author Jaime & Javier
 */
@Controller 
public class Controlador {

    @Autowired
    UsuarioDAO usuario_db;
    
    @Autowired
    CalificacionDAO calificacion_db;
    
    @Autowired
    ComentarioDAO comentario_db;
    
    @Autowired
    PuestoDAO puesto_db;
    
    public static Usuario usuarioActual;
    
    public static List <Comentario> comentarios;
    
    public static ArrayList <Puesto> resultadosBusqueda;
    
    public static Puesto puestoActual;
    
    @RequestMapping(value="/")
    public String inicio(){
        return "PaginaInicioIH";
    }
    
    public static List <Comentario> getComentarios(){
        return comentarios;
    }
    
    public static List <Puesto> getResultados(){
        return resultadosBusqueda;
    }
    
    public static Usuario getUsuarioActual(){
        return usuarioActual;
    }
    
    public static Puesto getPuestoActual(){
        return puestoActual;
    }
    
    @RequestMapping(value="/comentarioSeleccionado", method = RequestMethod.GET)
    public ModelAndView comentarioSeleccionado(ModelMap model,HttpServletRequest request){
        String comentarioTexto = request.getParameter("textoComentario");
        System.out.println(comentarioTexto);
        return paginaInicialIH(model,request);
    }
    
    @RequestMapping(value="/cerrarSesion", method = RequestMethod.GET)
    public ModelAndView cerrarSesion(ModelMap model,HttpServletRequest request){
        usuarioActual = null;
        return new ModelAndView("PaginaInicioIH",model);
    }
    
    @RequestMapping(value="/usuarioSeleccionado", method = RequestMethod.GET)
    public ModelAndView usuarioSeleccionado(ModelMap model,HttpServletRequest request){
        String idUS = request.getParameter("idUsuarioSeleccionado");
        System.out.println(idUS);
        return verPuestoIH(model,request);   
    }
    
    @RequestMapping(value="/iniciarSesionIH", method = RequestMethod.GET)
    public ModelAndView iniciarSesionIH(ModelMap model,HttpServletRequest request){
        return new ModelAndView("IniciarSesionIH",model);
    }
    
    @RequestMapping(value="/iniciarSesion", method = RequestMethod.GET)
    public ModelAndView iniciarSesion(ModelMap model,HttpServletRequest request){
        String correo = request.getParameter("correo");                
        String password = request.getParameter("contra");
        System.out.println("Intentando iniciar sesión con: " + correo + "," + password);
        usuarioActual = usuario_db.getUsuario(correo, password);
        if (usuarioActual==null){
            model.addAttribute("info","error");
            return new ModelAndView("IniciarSesionIH",model);
        }
        return paginaInicialIH(model,request);    
    }
    
    @RequestMapping(value="/registroIH", method = RequestMethod.GET)
    public ModelAndView registroIH(ModelMap model,HttpServletRequest request){
        return new ModelAndView("RegistroIH",model);            
    }
    
    @RequestMapping(value="/registrar", method = RequestMethod.GET)
    public ModelAndView registrar(ModelMap model,HttpServletRequest request){
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");                
        String password = request.getParameter("contra");
        String fecha = request.getParameter("fecha");
        String carrera = request.getParameter("carrera");
        if (correo==null || nombre==null || password==null){
            model.addAttribute("info","Error, todos los datos deben llenarse");
            return new ModelAndView("RegistroIH",model);
        }
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setPsswd(password);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setFoto(fecha);
        usuario_db.guardar(nuevoUsuario);
        usuarioActual = nuevoUsuario;
        return paginaInicialIH(model,request);
    }
    
    @RequestMapping(value="/comentar", method = RequestMethod.GET)
    public ModelAndView comentar(ModelMap model,HttpServletRequest request){
        String comentario = request.getParameter("comentario");
        /* User usuarioComentando = usuario_db.consulta(request.getAttribute("usuarioActual"));
        comentario_db.agregar(new Comentario(comentario,usuarioActual,request.getAttribute("ubicacion")));
        */
        return verPuestoIH(model,request);
    }
    
    @RequestMapping(value="/calificar", method = RequestMethod.GET)
    public ModelAndView calificar(ModelMap model,HttpServletRequest request){        
        String nuevaCalificacion = request.getParameter("nuevaCalificacion");
        int nC = Integer.parseInt(nuevaCalificacion);
        Calificacion c = new Calificacion();
        c.setEstrellas(nC);
        c.setPuesto(puestoActual);
        c.setUsuario(usuarioActual);
        imprimir(c.getEstrellas() + "<>" + c.getPuesto() + c.getUsuario());
        
        calificacion_db.guardar(c);
        puestoActual = puesto_db.getPuesto(puestoActual.getPuesto_id());
        return paginaInicialIH(model,request);
    }
    
    @RequestMapping(value="/paginaInicialIH", method = RequestMethod.GET)
    public ModelAndView paginaInicialIH(ModelMap model,HttpServletRequest request){
        if (usuarioActual==null){            
            return new ModelAndView("PaginaInicioIH",model);
        }
        model.addAttribute("nombre", usuarioActual.getNombre());
        model.addAttribute("correo", usuarioActual.getCorreo());
        model.addAttribute("password", usuarioActual.getPsswd());
        model.addAttribute("fecha", "06/06/6666");
        model.addAttribute("carrera", "ICC");
        return new ModelAndView("PaginaInicioIH",model);
    }    
    
    @RequestMapping(value="/verPuestoIH", method = RequestMethod.GET)
    public ModelAndView verPuestoIH(ModelMap model,HttpServletRequest request){
        int id_puesto = Integer.parseInt(request.getParameter("idPuesto"));
        puestoActual = puesto_db.getPuesto(id_puesto);
        String comidas, nombrePuesto, ubicacion, descripcion, calificacion;
        nombrePuesto = puestoActual.getNombre();
        ubicacion = "(" + puestoActual.getPosX() + "," + puestoActual.getPosY() + ")";           
        comentarios = puestoActual.getComentarios_usuario();
        
        calificacion = promedioLista(puestoActual.getCalificaciones_usuario()) + "";
        
        List<String> des = puestoActual.getTags();
        if (!des.isEmpty()){
            descripcion = des.get(0);
        } else {
            descripcion = "No hay comidas registradas";
        }        
        
        model.addAttribute("nombrePuesto", nombrePuesto);
        model.addAttribute("comidas",descripcion);
        model.addAttribute("comentarios",comentarios);
        model.addAttribute("ubicacion",ubicacion);
        model.addAttribute("descripcion",descripcion);
        model.addAttribute("calificacion",calificacion);
        model.addAttribute("usuarioActual","UsuarioActual");
        
        return new ModelAndView("VerPuestoIH",model);
    }    
    
    @RequestMapping(value="/busquedaIH", method = RequestMethod.GET)
    public ModelAndView busquedaIH(ModelMap model,HttpServletRequest request){
        return new ModelAndView("BusquedaIH",model);    
    }
    
    @RequestMapping(value="/agregarPuestoIH", method = RequestMethod.GET)
    public ModelAndView agregarPuestoIH(ModelMap model,HttpServletRequest request){        
        return new ModelAndView("AgregarPuestoIH",model);    
    }
    
    @RequestMapping(value="/busqueda", method = RequestMethod.GET)
    public ModelAndView busqueda(ModelMap model,HttpServletRequest request){
        String solicitud_busqueda = request.getParameter("nombrePuesto");
        resultadosBusqueda = puesto_db.getPuestos("%"+solicitud_busqueda+"%");        
        return resultadosBusqueda(model,request);
    }
    
    @RequestMapping(value="/resultadosBusqueda", method = RequestMethod.GET)
    public ModelAndView resultadosBusqueda(ModelMap model,HttpServletRequest request){        
        return new ModelAndView("ResultadosBusqueda",model);    
    }
    
    @RequestMapping(value="/agregarPuesto", method = RequestMethod.GET)
    public ModelAndView agregarPuesto(ModelMap model,HttpServletRequest request){        
        // Se obtienen los atributos
        String comidas, nombrePuesto, ubicacion, descripcion;
        ubicacion = request.getParameter("ubicacion");
        String [] coordenadas = ubicacion.split(",");
        int cordX = Integer.parseInt(coordenadas[0].substring(1));
        int cordY = Integer.parseInt(coordenadas[1].substring(0,coordenadas[1].length()-2));
        nombrePuesto = request.getParameter("nombrePuesto");
        comidas = request.getParameter("comidas");           
        descripcion = request.getParameter("descripcion"); 
        List<Calificacion> calificaciones = new ArrayList<Calificacion>();
        List<Comentario> comentarios = new ArrayList<Comentario>();
        String[] spam = comidas.split(",");
        List <String> nuevasComidas = Arrays.asList(spam);
        // Se asignan los atributos al nuevo puesto
        Puesto nuevoPuesto = new Puesto();
        nuevoPuesto.setNombre(nombrePuesto);
        nuevoPuesto.setPosX(cordX);
        nuevoPuesto.setPosY(cordX);           
        nuevoPuesto.setComentarios_usuario(comentarios);
        nuevoPuesto.setCalificaciones_usuario(calificaciones);
        nuevoPuesto.setTags(nuevasComidas);
        System.out.println("Agregando al puesto:  " + nuevoPuesto.getNombre() + ",[" + nuevoPuesto.getPosX() + "_" + nuevoPuesto.getPosY());
        puesto_db.guardar(nuevoPuesto);
        return new ModelAndView("PaginaInicioIH",model);    
    }
    
    private float promedioLista(List<Calificacion> calificaciones){
        float resultado = 0;
        int ponderacion = 0;
        for (Calificacion c : calificaciones){
            resultado+=c.getEstrellas();
            ponderacion++;
        }
        System.out.println(resultado +" "+ponderacion);
        if(ponderacion==0)
            return 0;
        return resultado/(float)ponderacion;
    }
    
    private void imprimir(Object o){
        System.out.println(o.toString());
        
    }
    
}
