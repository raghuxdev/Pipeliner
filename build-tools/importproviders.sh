cd ../custom-provider/

mvn clean install

cp target/*.jar ../build/bofurin/providers/custom-provider.jar

echo "Copied the provider jar from the custom-provider"

