package util;

import java.util.Objects;

/**
 * Eine Klasse, die ein generisches Paar darstellt.
 * <p>
 * Diese Datei darf nicht verändert werden.
 *
 * @param <L> Typ des linken Werts
 * @param <R> Typ des rechten Werts
 *
 * @author uhl, aan, avh, mhe, tti
 */
public final class Pair<L, R> {
    /** linker Wert */
    private L left;

    /** rechter Wert */
    private R right;

    /**
     * Konstruktor
     *
     * @param left  linker Wert
     * @param right rechter Wert
     */
    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Liefert den linken Wert des Paars zurück.
     *
     * @return linker Wert des Paars
     */
    public L l() {
        return this.left;
    }

    /**
     * Liefert den rechten Wert des Paars zurück.
     *
     * @return rechter Wert des Paars
     */
    public R r() {
        return this.right;
    }

    /**
     * Setzt den linken Wert des Paars.
     *
     * @param l neuer Wert für den linken Wert des Paars
     */
    public void setL(L l) {
        this.left = l;
    }

    /**
     * Setzt den rechten Wert des Paars.
     *
     * @param r neuer Wert für den rechten Wert des Paars
     */
    public void setR(R r) {
        this.right = r;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair<?, ?> other)) {
            return false;
        }

        return Objects.equals(this.left, other.left) && Objects.equals(this.right, other.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.left, this.right);
    }

    @Override
    public String toString() {
        return "Pair {" + "left=" + this.left + ", right=" + this.right + '}';
    }
}
