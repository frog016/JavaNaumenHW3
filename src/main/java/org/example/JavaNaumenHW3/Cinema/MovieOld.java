package org.example.JavaNaumenHW3.Cinema;

public class MovieOld {
    private Long id;
    private int genre;
    private float duration;
    private String description;

    public MovieOld(Long id, int genre, float duration, String description) {
        this.id = id;
        this.genre = genre;
        this.duration = duration;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "id = " + id + ", genre = " + genre +
                ", duration = " + duration + ", description = " + description;
    }
}
