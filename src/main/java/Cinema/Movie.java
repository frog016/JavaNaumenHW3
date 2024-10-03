package Cinema;

public class Movie {
    private Long id;
    private int genre;
    private float duration;
    private String description;

    public Movie(Long id, int genre, float duration, String description) {
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
                ", duration = " + duration + ", description = '" + description;
    }
}
