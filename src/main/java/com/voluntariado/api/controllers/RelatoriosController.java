package com.voluntariado.api.controllers;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voluntariado.jasper.ConnectionFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.SimpleJasperReportsContext;

@RestController
@RequestMapping("/api/v1/relatorios")
public class RelatoriosController implements Serializable {

	private static final long serialVersionUID = 6829904152028917623L;

	/**
	 * 
	 * Busca de um relatorio por um idUsuario e um idCertificado.
	 * 
	 * @param idUsuario
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws JRException
	 */
	@GetMapping("/{idUsuario}")
	public ResponseEntity<byte[]> get(@PathVariable("idUsuario") Long idUsuario) throws FileNotFoundException {
		ByteArrayOutputStream bos = null;
		Connection connection = null;
		byte[] bytesData = null;

		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("P_ALUNO_ID", 1);
			parametros.put("P_TURMA_ID", 1);

			bos = new ByteArrayOutputStream();
			connection = ConnectionFactory.getInstance().getConnection();

			String userDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();

			String reportPath = userDirectory
					+ "\\src\\main\\java\\com\\voluntariado\\api\\controllers\\certificado.jasper";

			JasperPrint print = JasperFillManager.fillReport(reportPath, parametros, connection);
			JasperReportsContext jrc = new SimpleJasperReportsContext();
			JasperExportManager jem = JasperExportManager.getInstance(jrc);
			jem.exportReportToPdfStream(print, bos);

			bytesData = bos.toByteArray();

			// TODO: Retirar, somente para teste.
			// Files.write(bytesData, new File("certificado.pdf"));
		} catch (JRException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				bos.close();
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
		}

		return ResponseEntity.ok(bytesData);
	}
}
