package br.com.ubs.bank.ubsbank.batch.reader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.json.JsonObjectReader;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;

public class ProductJsonObjectReader<T> implements JsonObjectReader<T> {

    private Class<? extends T> itemType;

    private JsonParser jsonParser;

    private ObjectMapper mapper = new ObjectMapper();

    private InputStream inputStream;



    public ProductJsonObjectReader(Class<? extends T> itemType) {
        this.itemType = itemType;
    }

    public void setMapper(ObjectMapper mapper) {
        Assert.notNull(mapper, "The mapper must not be null");
        this.mapper = mapper;
    }

    @Override
    public void open(Resource resource) throws Exception {
        Assert.notNull(resource, "The resource must not be null");
        this.inputStream = resource.getInputStream();
        this.jsonParser = this.mapper.getFactory().createParser(this.inputStream);

        JsonToken firstJsonToken = this.jsonParser.nextToken();
        JsonToken secondJsonToken = this.jsonParser.nextToken();
        JsonToken thirdJsonToken = this.jsonParser.nextToken();


        Assert.state((
                        firstJsonToken == JsonToken.START_OBJECT &&
                                secondJsonToken == JsonToken.FIELD_NAME &&
                                thirdJsonToken == JsonToken.START_ARRAY),
                "The Json input stream must start with an field data of Json objects");
    }

    @Nullable
    @Override
    public T read() throws Exception {
        try {
            if (this.jsonParser.nextToken() == JsonToken.START_OBJECT) {
                return this.mapper.readValue(this.jsonParser, this.itemType);
            }
        } catch (IOException e) {
            throw new ParseException("Unable to read next JSON object", e);
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        this.inputStream.close();
        this.jsonParser.close();
    }
}
