package turismouydesktop.gui.panels;

import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;

public interface ListActivityListener {
	
public void onListActivitySelected(DtTouristicActivity activity);
public void onListActivitySelectedDt(Long id);
}
