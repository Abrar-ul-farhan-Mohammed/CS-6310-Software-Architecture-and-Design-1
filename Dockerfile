# Compile our java files in this container
FROM openjdk:17-slim AS builder
COPY src /usr/src/cs6310/src
WORKDIR /usr/src/cs6310
RUN find . -name "*.java" | xargs javac -d ./target
RUN jar cfe streaming_wars.jar Main -C ./target/ .

# Copy the jar and test scenarios into our final image
FROM openjdk:17-slim
WORKDIR /usr/src/cs6310
COPY test_scenarios ./
COPY ./bin/* ./
COPY --from=builder /usr/src/cs6310/streaming_wars.jar ./streaming_wars.jar
CMD ["java", "-jar", "streaming_wars.jar", "commands_00.txt"]
