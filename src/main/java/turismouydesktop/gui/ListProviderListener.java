package turismouydesktop.gui;

import uy.turismo.servidorcentral.logic.datatypes.DtProvider;

public interface ListProviderListener {
	
	public void onProviderSelected(Long id);
	
	public void onProviderSelectedDt(DtProvider provider);
}
