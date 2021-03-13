package pl.redny.album.infrastructure.spring;

import com.fasterxml.jackson.databind.JavaType;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class VavrListSupportConverter implements ModelConverter {

    @Override
    public Schema resolve(AnnotatedType annotatedType, ModelConverterContext context, Iterator<ModelConverter> chain) {
        JavaType javaType = Json.mapper().constructType(annotatedType.getType());
        if (javaType != null) {
            Class<?> cls = javaType.getRawClass();
            if (io.vavr.collection.List.class.equals(cls)) {
                annotatedType = new AnnotatedType()
                        .type(javaType.containedType(0))
                        .ctxAnnotations(annotatedType.getCtxAnnotations())
                        .parent(annotatedType.getParent())
                        .schemaProperty(annotatedType.isSchemaProperty())
                        .name(annotatedType.getName())
                        .resolveAsRef(annotatedType.isResolveAsRef())
                        .jsonViewAnnotation(annotatedType.getJsonViewAnnotation())
                        .propertyName(annotatedType.getPropertyName())
                        .skipOverride(true);
                return this.resolve(annotatedType, context, chain);
            }
        }
        return (chain.hasNext()) ? chain.next().resolve(annotatedType, context, chain) : null;
    }
}
