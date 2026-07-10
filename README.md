# -_Contador_numeros_java_- :.
# ContadorNumerosJava:

<img width="1254" height="1254" alt="image" src="https://github.com/user-attachments/assets/fa54a0a6-4cdb-472f-b6a8-6f1f041fb93e" />  

<img width="2551" height="1040" alt="image" src="https://github.com/user-attachments/assets/98104e0e-d685-4ef9-ad1a-eee2359b48d5" />  

```
Proyecto muy básico desarrollado en **Java 21 + Consola + MySQL + JDBC**, donde el usuario ingresa una lista de **58 números**, el
sistema procesa la información, almacena los datos en una base de datos MySQL y posteriormente consulta la información registrada.

---

# Objetivos

Este proyecto permite:

- ✅ Ingresar una lista de **58 números** desde la consola.
- ✅ Contar la cantidad de números ingresados.
- ✅ Calcular:
  - Cantidad de números.
  - Suma total.
  - Promedio.
  - Número mayor.
  - Número menor.
- ✅ Guardar cada número en MySQL.
- ✅ Guardar un resumen del procesamiento.
- ✅ Consultar toda la información almacenada.
- ✅ Mostrar los resultados en consola.

---

# Tecnologías utilizadas

- Java 21
- Maven
- JDBC
- MySQL 8+
- IntelliJ IDEA (recomendado)

---

# Estructura del proyecto

```text
ContadorNumerosJava
│
├── pom.xml
├── script.sql
│
└── src
    └── main
        └── java
            └── com
                └── ejemplo
                    └── contador
                        │
                        ├── Main.java
                        ├── Conexion.java
                        ├── NumeroDAO.java
                        ├── ResumenDAO.java
                        ├── Numero.java
                        └── Resumen.java
```

---

# Base de datos MySQL

## Crear la base de datos

```sql
CREATE DATABASE contador_numeros;

USE contador_numeros;
```

---

## Tabla de números

```sql
CREATE TABLE numeros(

    id INT AUTO_INCREMENT PRIMARY KEY,

    numero INT

);
```

---

## Tabla de resumen

```sql
CREATE TABLE resumen(

    id INT AUTO_INCREMENT PRIMARY KEY,

    cantidad INT,

    suma INT,

    promedio DOUBLE,

    mayor INT,

    menor INT

);
```

---

# Archivo `pom.xml`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.ejemplo</groupId>

    <artifactId>ContadorNumerosJava</artifactId>

    <version>1.0</version>

    <properties>

        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>

    </properties>

    <dependencies>

        <dependency>

            <groupId>com.mysql</groupId>

            <artifactId>mysql-connector-j</artifactId>

            <version>9.3.0</version>

        </dependency>

    </dependencies>

</project>
```

---

# Clase `Conexion.java`

```java
package com.ejemplo.contador;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/contador_numeros";

    private static final String USER = "root";

    private static final String PASSWORD = "123456";

    public static Connection conectar() throws Exception {

        return DriverManager.getConnection(URL, USER, PASSWORD);

    }

}
```

---

# Clase `Numero.java`

```java
package com.ejemplo.contador;

public class Numero {

    private int numero;

    public Numero(int numero) {

        this.numero = numero;

    }

    public int getNumero() {

        return numero;

    }

}
```

---

# Clase `Resumen.java`

```java
package com.ejemplo.contador;

public class Resumen {

    private int cantidad;
    private int suma;
    private double promedio;
    private int mayor;
    private int menor;

    public Resumen(
            int cantidad,
            int suma,
            double promedio,
            int mayor,
            int menor) {

        this.cantidad = cantidad;
        this.suma = suma;
        this.promedio = promedio;
        this.mayor = mayor;
        this.menor = menor;

    }

    public int getCantidad() {
        return cantidad;
    }

    public int getSuma() {
        return suma;
    }

    public double getPromedio() {
        return promedio;
    }

    public int getMayor() {
        return mayor;
    }

    public int getMenor() {
        return menor;
    }

}
```

---

# Clase `NumeroDAO.java`

```java
package com.ejemplo.contador;

import java.sql.*;

public class NumeroDAO {

    public void guardarNumero(Numero numero) throws Exception {

        Connection con = Conexion.conectar();

        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO numeros(numero) VALUES(?)");

        ps.setInt(1, numero.getNumero());

        ps.executeUpdate();

        ps.close();
        con.close();

    }

    public void listar() throws Exception {

        Connection con = Conexion.conectar();

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT * FROM numeros");

        System.out.println();

        System.out.println("NUMEROS GUARDADOS");

        while (rs.next()) {

            System.out.println(
                    rs.getInt("id")
                            + " -> "
                            + rs.getInt("numero"));

        }

        rs.close();
        st.close();
        con.close();

    }

}
```

---

# Clase `ResumenDAO.java`

```java
package com.ejemplo.contador;

import java.sql.*;

public class ResumenDAO {

    public void guardar(Resumen r) throws Exception {

        Connection con = Conexion.conectar();

        PreparedStatement ps = con.prepareStatement(

                "INSERT INTO resumen(cantidad,suma,promedio,mayor,menor) VALUES(?,?,?,?,?)"

        );

        ps.setInt(1, r.getCantidad());

        ps.setInt(2, r.getSuma());

        ps.setDouble(3, r.getPromedio());

        ps.setInt(4, r.getMayor());

        ps.setInt(5, r.getMenor());

        ps.executeUpdate();

        ps.close();
        con.close();

    }

    public void mostrar() throws Exception {

        Connection con = Conexion.conectar();

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM resumen");

        System.out.println();

        System.out.println("RESUMEN");

        while (rs.next()) {

            System.out.println("Cantidad : " + rs.getInt("cantidad"));
            System.out.println("Suma : " + rs.getInt("suma"));
            System.out.println("Promedio : " + rs.getDouble("promedio"));
            System.out.println("Mayor : " + rs.getInt("mayor"));
            System.out.println("Menor : " + rs.getInt("menor"));
            System.out.println("----------------------");

        }

        rs.close();
        st.close();
        con.close();

    }

}
```

---

# Clase `Main.java`

```java
package com.ejemplo.contador;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        NumeroDAO numeroDAO = new NumeroDAO();

        ResumenDAO resumenDAO = new ResumenDAO();

        int cantidad = 58;

        int suma = 0;

        int mayor = Integer.MIN_VALUE;

        int menor = Integer.MAX_VALUE;

        System.out.println("INGRESE 58 NUMEROS");

        for (int i = 1; i <= cantidad; i++) {

            System.out.print("Numero " + i + ": ");

            int numero = sc.nextInt();

            suma += numero;

            if (numero > mayor) {

                mayor = numero;

            }

            if (numero < menor) {

                menor = numero;

            }

            numeroDAO.guardarNumero(new Numero(numero));

        }

        double promedio = (double) suma / cantidad;

        Resumen resumen = new Resumen(

                cantidad,

                suma,

                promedio,

                mayor,

                menor

        );

        resumenDAO.guardar(resumen);

        System.out.println();

        System.out.println("RESULTADOS");

        System.out.println("----------------");

        System.out.println("Cantidad : " + cantidad);

        System.out.println("Suma : " + suma);

        System.out.println("Promedio : " + promedio);

        System.out.println("Mayor : " + mayor);

        System.out.println("Menor : " + menor);

        numeroDAO.listar();

        resumenDAO.mostrar();

    }

}
```

---

# Flujo del programa

```text
Inicio
   │
   ▼
Ingresar 58 números
   │
   ▼
Guardar cada número en MySQL
   │
   ▼
Calcular

• Cantidad
• Suma
• Promedio
• Mayor
• Menor

   │
   ▼
Guardar resumen en MySQL
   │
   ▼
Consultar números almacenados
   │
   ▼
Consultar resumen
   │
   ▼
Mostrar resultados en consola
   │
   ▼
Fin
```

---

# Resultado esperado

```text
INGRESE 58 NUMEROS

Numero 1: 5
Numero 2: 10
...
Numero 58: 20

RESULTADOS

Cantidad : 58
Suma : 2865
Promedio : 49.39
Mayor : 98
Menor : 2

NUMEROS GUARDADOS

1 -> 5
2 -> 10
3 -> 15
...
58 -> 20

RESUMEN

Cantidad : 58
Suma : 2865
Promedio : 49.39
Mayor : 98
Menor : 2
```

---

# Consultas SQL útiles

## Ver todos los números

```sql
SELECT * FROM numeros;
```

---

## Ver el resumen

```sql
SELECT * FROM resumen;
```

---

## Contar registros almacenados

```sql
SELECT COUNT(*) FROM numeros;
```

---

## Obtener el promedio desde MySQL

```sql
SELECT AVG(numero) FROM numeros;
```

---

## Obtener el mayor valor

```sql
SELECT MAX(numero) FROM numeros;
```

---

## Obtener el menor valor

```sql
SELECT MIN(numero) FROM numeros;
```

---

# Posibles mejoras

Este proyecto puede ampliarse para hacerlo más completo, por ejemplo:

- Leer automáticamente los **58 números** desde un archivo Excel (`.xlsx`) utilizando **Apache POI**.
- Importar los datos desde un archivo CSV.
- Generar un reporte PDF con el resumen.
- Mostrar estadísticas adicionales:
  - Moda.
  - Mediana.
  - Varianza.
  - Desviación estándar.
- Agregar una interfaz gráfica con Swing o JavaFX.
- Exportar el resumen a Excel.
- Guardar la fecha y hora de cada procesamiento en MySQL.
- Implementar validaciones para evitar entradas no numéricas.
- Utilizar `try-with-resources` para cerrar automáticamente las conexiones JDBC.
- Incorporar el patrón MVC para mejorar la organización del proyecto.

---

# Autor

Proyecto de ejemplo desarrollado con:

- Java 21
- Maven
- JDBC
- MySQL
- Programación Orientada a Objetos (POO)

Ideal para aprender:

- Conexión a bases de datos mediante JDBC.
- Uso de DAOs.
- Procesamiento de listas numéricas.
- Persistencia de datos en MySQL.
- Organización de proyectos Java con Maven .
:. . / .
