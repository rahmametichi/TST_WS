package tst.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import Ejb_packge.Abonne;
import Ejb_packge.Abonnement;
import Ejb_packge.Utilisateur;
import TST_EJB.servies.GestionUtilisateurRemote;

@Path(value="tst")
public class TstResource {
	
	@EJB
	private GestionUtilisateurRemote GestionUtilisateur;
	private GestionUtilisateurRemote GestionAbonne;
	
	
	@GET
	@Produces("text/plain")
	public String TST() {
		return "welomme to TST";
	}
	
	
	@GET
	@Path(value="authentification/{login}/{pwd}")
	@Produces({MediaType.APPLICATION_JSON})
    public Utilisateur authentification(@PathParam(value="login")String login,
		                  @PathParam(value="pwd")String password){
	    return GestionUtilisateur.authentification(login,password);
	}
	
	
	@POST
	@Path(value ="addAb")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response addAb(Utilisateur abonne){
        GestionAbonne.addAbonne(abonne);
        return Response.status(Status.CREATED).entity("Ajout succesfull").build();
	}
	
	@PUT
	@Path(value ="updateAb")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response updateAb(Abonne abonne){
        GestionAbonne.modifyAbonne(abonne);
        return Response.status(Status.OK).entity("update succesfull").build();
	}
	
	@DELETE
	@Path("{cin}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response dropAb(@PathParam("cin") String cin){
        Abonne a = GestionAbonne.findAbonneByCin(Integer.parseInt(cin));
        GestionAbonne.deleteAbonne(a);
        return Response.status(Status.OK).entity("Remove succesfull").build();
	}
	
	@GET
	@Path(value ="searchAb")
	@Produces({MediaType.APPLICATION_JSON})
	public Abonnement searchAb(@QueryParam(value="id") Integer id){
        return GestionAbonne.findAbonnementById(id);
	}
	
	@GET
	@Path(value ="searchAllAb")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Abonnement> searchAllAb(@QueryParam(value="cin") Integer cin){
        return GestionAbonne.findAllAbonnement(cin);
	}
}
