package proyecto.pkg3p;
/**
 Se importan las libreria necesarias para el desarrollo del proyecto
 
*/
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * @author Andrey Ramirez Mata 
 * @author Allan Sandoval Zuñiga
 * @author Anthony Sibaja Granados
 * @author Diego Reyes Catón
 * 
 */


/*
Se declara la @class PROYECTO_3 la cual va a contener los componestes y 
variables del sistema
*/

public class PROYECTO_3 extends javax.swing.JFrame {

    private final String ruta = System.getProperties().getProperty("user.dir");
    DefaultTableModel INFO_TABLE = new DefaultTableModel();
    TableRowSorter colum;
    File padron = null;  
    BufferedReader lectorarc = null;
    FileReader lecarchi = null;

    /*
    Se utiliza @class Proyecto la cual funciona de contructor para las variables 
    y componentes del sistema 
    */
    public PROYECTO_3() {
        FondoPanel fondo = new FondoPanel();
        initComponents();
        this.setContentPane(fondo);
        initComponents(); 
        info_padron();
        
        setImageLabel(jLabel2, "src/imagenes/bandera.png");
        setImageLabel(jLabel3, "src/imagenes/bandera.png");
        setImageLabel(jLabel4, "src/imagenes/TSE.png");
    }
    
    /*
    Se crea la @class FandoPanel se encarga de cargar una imagen en el fondo de 
    las vista del sistema
    */
     class FondoPanel extends JPanel
    {
        private Image imagen;
        
        @Override
        public void paint(Graphics g)
        {
            imagen = new ImageIcon(getClass().getResource("/imagenes/uno.jpg")).getImage();
            
            g.drawImage(imagen,0, 0, getWidth(), getHeight(),this);
            
            setOpaque(false);
            
            super.paint(g);
        }
    }
     /*
     El metodo @method setImageLabel se encarga de cargar imagenes a la vista
     */
     private void setImageLabel(JLabel labelName, String root){
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon (
        image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
        this.repaint();
    }
     /*
     Este @method Subir_Informacion se encargar de tomar todos lo datos de un 
     fichero o archivo txt y presentarlo la vista del sistema
     @param fila: toma la primer linea de texto del archivo txt
     @param lectorA: almacena el informacion tomada del la primera linea
     */
  
    public void Subir_Informacion(String fila, BufferedReader lectorA) 
            throws IOException {
        String[] datos = fila.split(",");
        INFO_TABLE.addRow(datos);
        if ((fila = lectorA.readLine()) != null) {
            Subir_Informacion(fila, lectorA);
        }
        mostrar_datos.setModel(INFO_TABLE);

    }
    /*
    El @method info_padron se encarga de cargar la informacion del archivo txt o
    fichero a la tabla en la vista.
    */
     public void info_padron() {

        try {
            padron = new File(ruta + "//PUGOLFITO2.txt");
            lecarchi = new FileReader(padron);
            lectorarc = new BufferedReader(lecarchi);

            String linea;
            insertar_informacion();
            if ((linea = lectorarc.readLine()) != null) {
                Subir_Informacion(linea, lectorarc);
            }

        } catch (IOException e) {
        } finally {
            try {
                if (null != lecarchi) {
                    lecarchi.close();
                }
            } catch (IOException e2) {
            }
        }
    }
    
    /*
     El @method insertar_informacion se encarga de cargar la informacion extra-
     ida de los archivos txt o ficheros en las columnas de las tablas segun los
     establecido
     
    */
     
    public void insertar_informacion() {
        INFO_TABLE.addColumn("CEDÚLA");
        
        INFO_TABLE.addColumn("C_ELECTORAL");
        
        INFO_TABLE.addColumn("GÉNERO");
        
        INFO_TABLE.addColumn("VENCIMIENTO");
        
        INFO_TABLE.addColumn("J_ELECTORAL");
        
        INFO_TABLE.addColumn("NOMBRE");
        
        INFO_TABLE.addColumn("APELLIDO");
        
        INFO_TABLE.addColumn("APELLIDO");

    }

   /*
    Este @method recorrer_archivo recorre el archivo mientras realiza un conteo
    de los nombres que se repiten en el padron
    @param nombre: nombre con el que se evalua la repitencia 
    */
    public void recorrer_archivo(String nombre) {
        try {
            padron = new File(ruta + "//PUGOLFITO2.txt");
            lecarchi = new FileReader(padron);
            lectorarc = new BufferedReader(lecarchi);

            String fila = "";
            
            Cantidad_Nombres(fila,nombre,lectorarc,  -1);
        } catch (IOException e) {
        } finally {
            try {
                if (null != lecarchi) {
                    lecarchi.close();
                }
            } catch (IOException e2) {
            }
        }    }
    
    
    /*
    El @method Cantidad_Nombres se encarga de almacenar los nombres contados y 
    realizar una sumatoria para saber el total
    @param filas: se encarga de almacenar la informacion en filas del archivo
    @param nombre: nombre con el que se evalua la repitencia 
    @param lector: almacena la informacion del dato obtenido en las filas del 
    archivo 
    @param contador: cuenta la cantidad de veces que se repite nombre
    */
    public void Cantidad_Nombres(String filas, String nombre, BufferedReader lector,  
            int contador) throws IOException {

        if ((filas = lector.readLine()) != null) {

            String[] datos = filas.split(",");

            if (datos[5].equals(nombre)) {

                contador++;
            }

            Cantidad_Nombres(filas,  nombre, lector, contador);

        } else {
            personar_repetida.setText(String.valueOf(contador));
        }

    }
    /*
    El @method Eleccion_Tipo se encarga de elegir el tipo de dato que deseamos 
    consultar
    */
    public void Eleccion_Tipo() {

        if (datos.getSelectedItem().toString() == "Cedula") {
            Consultar_Id();
        } else if (datos.getSelectedItem().toString() == "Nombre") {
            Consultar_Nombre();
        } else if (datos.getSelectedItem().toString() == "Apellido") {
            Consultar_Apellido();
        } else if (datos.getSelectedItem().toString() == "Vencimiento Cedula") {
            consultar_fecha_vencimiento();
        }

    }
 
    /*
    El @method Consultar_Nombre  busca la informacion del nombre digitado y la
    compara con el resto de datos hasta encontrar el dato 
    */
    public void Consultar_Nombre() {
        recorrer_busca.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                try {
                    colum.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)"
                            + recorrer_busca.getText(), 5));
                } catch (Exception e) {

                }

            }

        });
        colum = new TableRowSorter(INFO_TABLE);
        mostrar_datos.setRowSorter(colum);
    }
    
     /*
    El @method Consultar_Id busca la informacion de la cedula digitada y la com-
    para con el resto de datos hasta encontrarla  
    */
    
    public void Consultar_Id() {
        recorrer_busca.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                try {
                    Integer.parseInt(recorrer_busca.getText());
                    colum.setRowFilter(javax.swing.RowFilter.regexFilter(
                            recorrer_busca.getText(), 0));
                } catch (Exception e) {

                }

            }

        });
        colum = new TableRowSorter(INFO_TABLE);
        mostrar_datos.setRowSorter(colum);
    }
    
    /*
    El @method Consultar_Apellido  busca la informacion del apellido digitado y 
    la compara con el resto de datos hasta encontrar el dato  
    */
    
    public void Consultar_Apellido() {
        recorrer_busca.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                try {
                    colum.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" 
                            + recorrer_busca.getText(), 6));
                } catch (Exception e) {

                }

            }

        });
        colum = new TableRowSorter(INFO_TABLE);
        mostrar_datos.setRowSorter(colum);
    }
    
    
    

    /*
     El @method consultar_fecha_vencimiento se encarga de consultar los datos de 
    vencimiento de la cedula
     */
    public void consultar_fecha_vencimiento() {
        recorrer_busca.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                try {
                    Integer.parseInt(recorrer_busca.getText());
                    colum.setRowFilter(javax.swing.RowFilter.regexFilter(
                            recorrer_busca.getText(), 3));
                } catch (Exception e) {

                }

            }

        });
        colum = new TableRowSorter(INFO_TABLE);
        mostrar_datos.setRowSorter(colum);
    }

    
    /*
        El @method Calcular_Provincia se encarga de calcular la provincia a la 
    que pertenece la persona consultada
    @param cedula: por medio de este parametro se puede definir la provincia a
    la que pertenece la persona consultada
    */
    public void Calcular_Provincia(String cedula) {

        char[] vector = cedula.toCharArray();

        switch (vector[0]) {
            case '1':
                lugar_provincia.setText("San jose");
                break;
            case '2':
                lugar_provincia.setText("Alajuela");
                break;
            case '3':
                lugar_provincia.setText("Cartago");
                break;
            case '4':
                lugar_provincia.setText("Heredia");
                break;
            case '5':
                lugar_provincia.setText("Guanacaste");
                break;
            case '6':
                lugar_provincia.setText("Puntarenas");
                break;
            case '7':
                lugar_provincia.setText("Limón");
                break;
            case '8':
                lugar_provincia.setText("Extranjero");
                break;
            case '9':
                lugar_provincia.setText("Casos especiales");
                break;

            default:
                System.out.println("Default");
        }

    }

    /*
    El @mehtod Vencimiento_Cedula se encarga de calcular de la diferencia entre 
    la fecha actual y la fecha de vencimiento de la cedula con el fin de definir 
    si esta por vencer, vencida o vigente
    @param venc:  En el que se almacena la fecha de vencimiento de la cedula
    */
    public void Vencimiento_Cedula(String venc) throws IOException {
        
        String sAnio = venc.substring(0,4);
        String sMes = venc.substring(4,6);
        String sDia = venc.substring(6,8);
        Calendar fechaVenc = Calendar.getInstance();
        Calendar fechaActual = Calendar.getInstance();

        try {
            int anio = Integer.parseInt(sAnio);
            int mes = Integer.parseInt(sMes);
            int dia = Integer.parseInt(sDia);
            fechaVenc.set(anio, mes-1, dia);
            int aniosDiferencia = calcularAnnos(dia, mes, anio);
            int meses = (aniosDiferencia * 12) + (fechaVenc.get(Calendar.MONTH) - fechaActual.get(Calendar.MONTH));
            if(fechaActual.get(Calendar.DATE)>dia){
                meses--;
            }
            if(meses > 0){
                vencimiento.setText("No");
            }
            else if(meses == 0){
                vencimiento.setText("Si");
            }
            else{
                vencimiento.setText("Ya se encuentra vencida");
            }
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    }
    
    /*
    El @method calcularAnnos Se encarga de calcular la cantidad de annos de di-
    ferencia entre la fecha actual y la fecha de vencimiento de la cedula 
    @param dia: almacena el dia de vencimiento de la cedula
    @param mes  almacena el mes de vencimeinto de la cedula 
    @param anio: almacena el anio de vencimento de la cedula 
    */
    public static int calcularAnnos(int dia, int mes, int anio){
        Calendar fechaVenc = Calendar.getInstance();
        fechaVenc.set(anio, mes-1, dia);
        Calendar fechaActual = Calendar.getInstance();
        
        int diferencia = fechaVenc.get(Calendar.YEAR) - fechaActual.get(Calendar.YEAR);
        
        if(fechaActual.get(Calendar.DAY_OF_YEAR) > fechaVenc.get(Calendar.DAY_OF_YEAR)){
            diferencia--;
        }
        return diferencia;
    }
   
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        buscar_persona = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mostrar_datos = new javax.swing.JTable();
        recorrer_busca = new javax.swing.JTextField();
        datos = new javax.swing.JComboBox<>();
        btnCerrar = new javax.swing.JButton();
        jPanel1 = new FondoPanel();
        personas_nombres_repetidos = new javax.swing.JLabel();
        cedula_vencimiento_ = new javax.swing.JLabel();
        lugar_pertenece = new javax.swing.JLabel();
        personar_repetida = new javax.swing.JLabel();
        lugar_provincia = new javax.swing.JLabel();
        vencimiento = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 25)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONSULTAS PADRON ELECTORAL COSTA RICA ");

        buscar_persona.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        buscar_persona.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buscar_persona.setText("FILTRAR POR:");

        mostrar_datos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        mostrar_datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        mostrar_datos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrar_datosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(mostrar_datos);

        recorrer_busca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recorrer_buscaActionPerformed(evt);
            }
        });
        recorrer_busca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                recorrer_buscaKeyTyped(evt);
            }
        });

        datos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cedula", "Nombre", "Apellido", "Vencimiento Cedula" }));
        datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datosActionPerformed(evt);
            }
        });

        btnCerrar.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnCerrar.setText("SALIR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        personas_nombres_repetidos.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        personas_nombres_repetidos.setText("CANTIDAD NOMBRES REPETIDOS:");

        cedula_vencimiento_.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        cedula_vencimiento_.setText("CEDULA POR VENCER:");

        lugar_pertenece.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        lugar_pertenece.setText("PROVINCIA:");

        vencimiento.setDoubleBuffered(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lugar_provincia, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(personar_repetida, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lugar_pertenece, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(personas_nombres_repetidos, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(vencimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cedula_vencimiento_, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
                .addGap(0, 45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(personas_nombres_repetidos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(personar_repetida, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lugar_pertenece, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lugar_provincia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cedula_vencimiento_, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel1)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(datos, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(recorrer_busca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                                .addGap(33, 33, 33))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(buscar_persona))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buscar_persona, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(recorrer_busca, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /*
       El @method mostrar_datosMouseClicked se encarga de mostrar los datos 
       seleccionados con el cursor
    */
    private void mostrar_datosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mostrar_datosMouseClicked
        int seleccion = mostrar_datos.rowAtPoint(evt.getPoint());
        try {
            Vencimiento_Cedula(String.valueOf(mostrar_datos.getValueAt(seleccion, 3)));
        } catch (IOException ex) {
            Logger.getLogger(PROYECTO_3.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calcular_Provincia(String.valueOf(mostrar_datos.getValueAt(seleccion, 0)));
        recorrer_archivo(String.valueOf(mostrar_datos.getValueAt(seleccion, 5)));
    }//GEN-LAST:event_mostrar_datosMouseClicked
    /*
    El @method btnCerrarActionPerformed se encarga de finalizar la ejecucion del 
    programa
    */
    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed
    /*
    El @mehtod recorrer_buscaKeyTyped se encarga de seleccionar la opcion de 
    filtrado seleccionada
    */
    private void recorrer_buscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recorrer_buscaKeyTyped
        Eleccion_Tipo();
    }//GEN-LAST:event_recorrer_buscaKeyTyped
    /*
    El @method datosActionPerformed null
    */
    private void datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datosActionPerformed
        
    }//GEN-LAST:event_datosActionPerformed
     /*
    El @method recorrer_buscaActionPerformed null
    */
    private void recorrer_buscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recorrer_buscaActionPerformed
        
    }//GEN-LAST:event_recorrer_buscaActionPerformed
      
    
    /*
    El @method main permite y realiza la ejecucion del programa 
    */
  
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PROYECTO_3().setVisible(true);
            }
        });
    }
    
  

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel buscar_persona;
    private javax.swing.JLabel cedula_vencimiento_;
    private javax.swing.JComboBox<String> datos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lugar_pertenece;
    private javax.swing.JLabel lugar_provincia;
    private javax.swing.JTable mostrar_datos;
    private javax.swing.JLabel personar_repetida;
    private javax.swing.JLabel personas_nombres_repetidos;
    private javax.swing.JTextField recorrer_busca;
    private javax.swing.JLabel vencimiento;
    // End of variables declaration//GEN-END:variables
}
