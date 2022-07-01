package Model;

public class ModelMois {
	public ModelMois() {}
	public String NumberMois(String date) {
		String mois;
		switch (date) {
		case "Janvier":
			mois = "01";
			break;
		case "Février":
			mois = "02";
			break;
		case "Mars":
			mois = "03";
			break;
		case "Avril":
			mois = "04";
			break;
		case "Mai":
			mois = "05";
			break;
		case "Juin":
			mois = "06";
			break;
		case "Juillet":
			mois = "07";
			break;
		case "Août":
			mois = "08";
			break;
		case "Septembre":
			mois = "09";
			break;
		case "Octobre":
			mois = "10";
			break;
		case "Novembre":
			mois = "11";
			break;
		default:
			mois = "12";
			break;

		}
		return mois;
	}
public String StringMois(String date) {
	String mois;
	switch (date) {
	case "01":mois="Janvier";						
		break;
	case "02":mois="Février";						
	break;
			
	case "03":mois="Mars";						
	break;
	case "04": mois="Avril";						
	break;
	case "05":mois="Mai";						
	break;
	case "06":mois="Juin";						
	break;
	case "07":mois="Juillet";						
	break;
	case "08":mois="Août";						
	break;
	case "09":mois="Septembre";						
	break;
	case "10":mois="Octobre";						
	break;
	case "11":mois="Novembre";						
	break;
	default:mois="Décembre";						
	break;
	}
	return mois;
	
}

}
