package turismouydesktop.gui.panels;

import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;

public interface ListTouristicBundleListener {

	public void onListTouristicBundle(Long id);

	public void reLoadListBundle(DtTouristicBundle bundle);

	public void onListTouristicBundleDt(DtTouristicBundle dtBundle);
}
