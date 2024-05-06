package Keywords.helpers

import internal.GlobalVariable
import java.security.MessageDigest
import org.bouncycastle.jcajce.provider.digest.RIPEMD160;
import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import groovy.json.JsonSlurper

public class WebServicesMethods {

	/**
	 * Method Description: This method used to get a response body
	 * @param path
	 * @return
	 */
	def getResponse(String path) {
		RequestObject request = findTestObject(path)
		ResponseObject response = WS.sendRequest(request)
		return response
	}

	/**
	 * Method Description: This method used to Verify status code as 200
	 * @param res
	 * @return
	 */
	def verifyStatusCode(ResponseObject res){
		WS.verifyResponseStatusCode(res, 200)
		println(res.getStatusCode())
		assertThat(res.getStatusCode()).isEqualTo(200)
	}

	/**
	 * Method Description: This method used to Verify success status code
	 * @return
	 */
	def verifySuccessfulStatusReq() {
		ResponseObject response = getResponse()
		WS.verifyResponseStatusCode(response, 200)
		assertThat(response.getStatusCode()).isIn(Arrays.asList(200, 201, 202))
	}

	/**
	 * Method Description: This method used to generate a response hash using SHA1 algorithm
	 * @param input
	 * @return
	 */
	def generateSHA1(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1")
			byte[] hashBytes = digest.digest(input.getBytes("UTF-8"))
			StringBuilder hexString = new StringBuilder()
			hashBytes.each { byte b ->
				String hex = Integer.toHexString(0xff & b)
				if (hex.length() == 1) hexString.append('0')
				hexString.append(hex)
			}
			return hexString.toString().toUpperCase()
		} catch (Exception e) {
			e.printStackTrace()
			return null
		}
	}

	/**
	 * Method Description: This method used to generate a response hash using SHA256 algorithm
	 * @param input
	 * @return
	 */

	def generateSHA256(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256")
			byte[] hashBytes = digest.digest(input.getBytes("UTF-8"))
			StringBuilder hexString = new StringBuilder()
			hashBytes.each { byte b ->
				String hex = Integer.toHexString(0xff & b)
				if (hex.length() == 1) hexString.append('0')
				hexString.append(hex)
			}
			return hexString.toString().toUpperCase()
		} catch (Exception e) {
			e.printStackTrace()
			return null
		}
	}



	/**
	 * Method Description: This method used to generate a response hash using Blake2b algorithm
	 * @param input
	 * @return
	 */




	def generateBLAKE2b(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("BLAKE2b-256")  // Specify 256-bit version
			byte[] hashBytes = digest.digest(input.getBytes("UTF-8"))
			StringBuilder hexString = new StringBuilder()
			hashBytes.each { byte b ->
				String hex = Integer.toHexString(0xff & b)
				if (hex.length() == 1) hexString.append('0')
				hexString.append(hex)
			}
			return hexString.toString().toUpperCase()
		} catch (Exception e) {
			e.printStackTrace()
			return null
		}
	}
}
