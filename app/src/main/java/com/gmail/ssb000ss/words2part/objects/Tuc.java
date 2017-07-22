
package com.gmail.ssb000ss.words2part.objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "phrase",
    "meanings",
    "meaningId",
    "authors"
})
public class Tuc {

    @JsonProperty("phrase")
    private Phrase phrase;
    @JsonProperty("meanings")
    private List<Meaning> meanings = null;
    @JsonProperty("meaningId")
    private Object meaningId;
    @JsonProperty("authors")
    private List<Integer> authors = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("phrase")
    public Phrase getPhrase() {
        return phrase;
    }

    @JsonProperty("phrase")
    public void setPhrase(Phrase phrase) {
        this.phrase = phrase;
    }

    @JsonProperty("meanings")
    public List<Meaning> getMeanings() {
        return meanings;
    }

    @JsonProperty("meanings")
    public void setMeanings(List<Meaning> meanings) {
        this.meanings = meanings;
    }

    @JsonProperty("meaningId")
    public Object getMeaningId() {
        return meaningId;
    }

    @JsonProperty("meaningId")
    public void setMeaningId(Object meaningId) {
        this.meaningId = meaningId;
    }

    @JsonProperty("authors")
    public List<Integer> getAuthors() {
        return authors;
    }

    @JsonProperty("authors")
    public void setAuthors(List<Integer> authors) {
        this.authors = authors;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
