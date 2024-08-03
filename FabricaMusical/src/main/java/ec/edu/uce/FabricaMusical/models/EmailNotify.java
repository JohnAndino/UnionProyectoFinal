package ec.edu.uce.FabricaMusical.models;

import ec.edu.uce.FabricaMusical.models.interfaces.Notify;

public class EmailNotify implements Notify {
    @Override
    public void notifyClient(Product product) {
        System.out.println("Notificando al cliente que el producto " + product.getName() + " ha sido fabricado.");
    }
}
