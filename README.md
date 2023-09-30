# TareaApiRest
# Hector Diaz, Tomas Ariza , Ivan Gutierrez








##Mutation:

###Los mutation test son tests en los cuales se edita el codigo para que la prueba salga mal. Esto con el proposito de que si se edita el codigo de forma erronea la prueba falle y asi demostrar que esta ultima funciona
### En cuanto a los Mutation Test, en codigo la forma de realizarlo es la siguiente
###plugins{
###id("info.solidsoft.pitest") version "1.9.0"
###}

###pitest {
### junit5PluginVersion.set("1.0.0")
###excludedClasses.addAll(
###	"co/edu/unisabana/lealtadcliente/controller/dto.**",
###	"co/edu/unisabana/lealtadcliente/bd.**"
###)
###}

###Sin embargo, no funciona y sale lo siguiente "Execution failed for task ':pitest'.Process 'command 'C:\Users\Hector\.jdks\openjdk-20.0.2\bin\java.exe'' finished with non-zero exit value 1" Y ejecutamos el comando "./gradlew pitest --stacktrace" el cual nos permite obtener un rastro de lo que salio mal y analizando esto el mensaje especifico fue el siguiente "error: invalid source release: 20" decidimos buscar y el error es la version de java ya que buscamos y decia lo siguiente "Pitest is still under development, and there may be some known issues with using it with Java 20"