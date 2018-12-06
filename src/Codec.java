public interface Codec {

    String kodiere(String klartext);
    String dekodiere(String geheimtext);
    String gibLosung();
    void setzeLosung(String schluessel)throws IllegalArgumentException; // bei ungeeignetem Schlüssel
}
