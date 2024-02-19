import java.util.Scanner;

public class temporizador {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Variables para la entrada del usuario
        String diaDeLaSemana;
        int horas, minutos;

        do {
            System.out.print("Ingrese un día de la semana (lunes a viernes): ");
            diaDeLaSemana = scanner.nextLine().toLowerCase();
            if (!diaDeLaSemanaValido(diaDeLaSemana)) {
                System.out.print("Día no válido. Intente de nuevo.");
                continue;
            }
            System.out.print("Ingrese hora con minutos en formato 24 horas: ");
            String[] entradaHoraMinutos = scanner.nextLine().split(":");

            if (entradaHoraMinutos.length != 2) {
                System.out.println("Datos incorrectos. Vuelve a intentarlo, por favor");
                continue;
            }
            try {
                horas = Integer.parseInt(entradaHoraMinutos[0]);
                minutos = Integer.parseInt(entradaHoraMinutos[1]);
            } catch (NumberFormatException e) {
                System.out.println("Formato de hora incorrecto. Intente de nuevo.");
                continue;
            }
            if (!esHoraValida(horas) || !esMinutosValidos(minutos)) {
                System.out.println("Error: Hora o minutos no válidos. Intente de nuevo.");
                continue;
            }

            break; // Salir del bucle si los datos son válidos
        } while (true);

        int minutosFaltantesParaFinde = calcularMinutosHastaFinDeSemana(diaDeLaSemana, horas, minutos);
        System.out.println("Faltan " + minutosFaltantesParaFinde + " minutos para el fin de Semana");
    }

    private static boolean diaDeLaSemanaValido(String diaSemana) {
        return diaSemana.equals("lunes") || diaSemana.equals("martes") || diaSemana.equals("miercoles") ||
                diaSemana.equals("jueves") || diaSemana.equals("viernes");
    }

    private static boolean esHoraValida(int horas) {
        return horas >= 0 && horas <= 23;
    }

    private static boolean esMinutosValidos(int minutos) {
        return minutos >= 0 && minutos <= 59;
    }

    // Función para calcular los minutos hasta el fin de semana
    private static int calcularMinutosHastaFinDeSemana(String diaSemana, int horas, int minutos) {
        int minutosTotales = 0;

        // Convertir el día de la semana a un número (lunes=1, martes=2, ..., viernes=5)
        int numeroDiaSemana = 0;
        switch (diaSemana) {
            case "lunes":
                numeroDiaSemana = 1;
                break;
            case "martes":
                numeroDiaSemana = 2;
                break;
            case "miercoles":
                numeroDiaSemana = 3;
                break;
            case "jueves":
                numeroDiaSemana = 4;
                break;
            case "viernes":
                numeroDiaSemana = 5;
                break;
        }

        // Calcular minutos hasta el fin de semana (viernes a las 15:00)
        minutosTotales = ((5 - numeroDiaSemana) * 24 * 60) + ((15 - horas) * 60) - minutos;

        return minutosTotales;
    }
}
