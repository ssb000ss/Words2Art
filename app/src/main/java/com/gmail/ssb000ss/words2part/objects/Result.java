
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
    "result",
    "tuc",
    "phrase",
    "from",
    "dest",
    "authors"
})
public class Result {

    @JsonProperty("result")
    private String result;
    @JsonProperty("tuc")
    private List<Tuc> tuc = null;
    @JsonProperty("phrase")
    private String phrase;
    @JsonProperty("from")
    private String from;
    @JsonProperty("dest")
    private String dest;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("result")
    public String getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(String result) {
        this.result = result;
    }

    @JsonProperty("tuc")
    public List<Tuc> getTuc() {
        return tuc;
    }

    @JsonProperty("tuc")
    public void setTuc(List<Tuc> tuc) {
        this.tuc = tuc;
    }

    @JsonProperty("phrase")
    public String getPhrase() {
        return phrase;
    }

    @JsonProperty("phrase")
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    @JsonProperty("from")
    public String getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(String from) {
        this.from = from;
    }

    @JsonProperty("dest")
    public String getDest() {
        return dest;
    }

    @JsonProperty("dest")
    public void setDest(String dest) {
        this.dest = dest;
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
