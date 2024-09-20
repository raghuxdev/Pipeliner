
cd ..

echo "removing previous build"
rm -rf build

echo "removed previous build"

mkdir build

mkdir build/bofurin

echo "furin build started"

mvn clean install -e

echo "furin build completed"

cp ./dist/target/*.tar.gz ./build/dist.tar.gz

tar -zxf ./build/dist.tar.gz  -C ./build/bofurin/

echo "unpacked tar.gz file"

rm -rf ./build/dist.tar.gz





