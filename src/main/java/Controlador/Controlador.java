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

/**
 * @author Julio
 */
@Controller 
public class Controlador {

    /*@Autowired
    UsuarioDAO usuario_db;*/
    
    /*@Autowired
    CalificacionDAO calificacion_db;*/
    
    /*@Autowired
    ComidaDAO comida_db;*/
    
    /*@Autowired
    ComentarioDAO comentario_db;*/
    
    /*@Autowired
    PuestoDAO puesto_db;*/
    
    @RequestMapping(value="/")
    public String inicio(){
        return "PaginaInicioIH";
    }
    
    @RequestMapping(value="/persona2", method = RequestMethod.POST)
    public ModelAndView persona2(ModelMap model,HttpServletRequest request){
        /* Obtenemos el correo y el password del usuario
        String p1 = request.getParameter("correo");
        String p2 = request.getParameter("contra");
        // Consultamos para ver si el usuario existe
        User user = user_db.getUser(p1);
        Persona persona = null;
        if(user == null)
           return new ModelAndView("inicio", model); 
        persona = user.getPersona();
        String p3 = "";
        String p4 = "";
        String p5 = "";
        
        if(p2.equals(user.getPassword())){
            p3 = persona.getNombre();
            p4 = persona.getFechanac().toString();
            p5 = persona.getCarrera();
        }        
        // Agregamos los atributos al JSP
        model.addAttribute("correo", p1);
        model.addAttribute("contra", p2);
        model.addAttribute("nombre", p3);
        model.addAttribute("fecha", p4);
        model.addAttribute("carrera", p5);        */
        return new ModelAndView("persona",model); 
    }
    
    @RequestMapping(value="/iniciarSesionIH", method = RequestMethod.GET)
    public ModelAndView iniciarSesionIH(ModelMap model,HttpServletRequest request){
        String sesion = "Iniciar Sesion";
        Object o = request.getParameter("sesion");
        if (o!=null){
            sesion = (String) o;
        } model.addAttribute("sesion", sesion);
        return new ModelAndView("IniciarSesionIH",model);    
    }
    
    @RequestMapping(value="/registroIH", method = RequestMethod.GET)
    public ModelAndView registroIH(ModelMap model,HttpServletRequest request){
        return new ModelAndView("RegistroIH",model);            
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
        /* User usuarioCalificando = usuario_db.consulta(request.getAttribute("usuario"));
          Puesto p = puesto_db.consulta(request.getAttribute("ubicacion"));          
          if (usuarioCalificando.yaHaCalificado(p){
            return errorIH(3); // Bandera de que ya se ha calificado un puesto
          } else {
            puesto_db.actualizaCalificacion(p,nC);
            return verPuestoIH(model.request);
          }          
        */
        return verPuestoIH(model,request);
    }
    
    @RequestMapping(value="/paginaInicialIH", method = RequestMethod.GET)
    public ModelAndView paginaInicialIH(ModelMap model,HttpServletRequest request){    
        /* if (usuario==null){
        } else {
        }
        */
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");        
        String password = request.getParameter("contra");
        String fecha = request.getParameter("fecha");
        String carrera = request.getParameter("carrera");
        System.out.println(request.getAttribute("calificacion"));
        model.addAttribute("nombre", nombre);
        model.addAttribute("correo", correo);
        model.addAttribute("password", password);
        model.addAttribute("fecha", fecha);
        model.addAttribute("carrera", carrera);
        return new ModelAndView("PaginaInicioIH",model);
    }
    
    @RequestMapping(value="/verPuestoIH", method = RequestMethod.GET)
    public ModelAndView verPuestoIH(ModelMap model,HttpServletRequest request){
        String comidas, nombrePuesto, ubicacion, descripcion, calificacion, usuarioActual;
        Object comentarios [] = {"Tan sucio como su barba","Yummy"};
        //Puesto p;
        // usuarioActual = obtenerUsuarioActual();
        // ArrayList<Comentario> comentarios;
        // comentarios = p.comentarios();
        usuarioActual = "Usuario Comentador";
        ubicacion = request.getParameter("ubicacion");
        nombrePuesto = request.getParameter("nombrePuesto");
        comidas = request.getParameter("comidas");           
        descripcion = request.getParameter("descripcion"); 
        calificacion = "10"; // 10 es la calificación por defecto        
        if (nombrePuesto==null){ // Si no se ha dado un nombre de puesto, es porque se ha solicitado consulta desde un pin del mapa            
            comidas = "Hamburguesas";
            nombrePuesto  = "Harry el Sucio";
            ubicacion = "(0,0)";
            descripcion = "Yarg!";
            calificacion = "10";
            // Si esto pasa, se solicito un puesto desde el mapa
            /* p = puesto_db.consulta(llave1,llave2);
            nombrePuesto = p.getNombre();
            comidas = p.getComidas();
            ubicacion = p.getUbicacion();
            descripcion = p.getDescripcion();
            calificacion = p.getCalificacion();
            return new ModelAndView("VerPuestoIH",model);
            */
        } else { // De lo contrario, se está agregando uno nuevo
            String foods [] = comidas.split(",");
            String llaves [] = ubicacion.split(",");
            int llave1 = Integer.parseInt(llaves[0].substring(1)), llave2 = Integer.parseInt(llaves[1].substring(0,llaves[1].length()-2));            
            /* ArrayList<Comentario> com = new ArrayList();
            p = new Puesto(puesto_db.nuevoID(),nombrePuesto,llave1,llave2,descripcion,calificacion); 
            for (String s : foods){ // Se agregan las comidas
                comida_db.agrega(new Comida(s,llave1,llave2));
            } 
            */
        } model.addAttribute("nombrePuesto", nombrePuesto);
        model.addAttribute("comidas",comidas);        
        model.addAttribute("comentarios",comentarios);
        model.addAttribute("ubicacion",ubicacion);
        model.addAttribute("descripcion",descripcion);
        model.addAttribute("calificacion",calificacion);
        model.addAttribute("usuarioActual",comidas);
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
    
}
