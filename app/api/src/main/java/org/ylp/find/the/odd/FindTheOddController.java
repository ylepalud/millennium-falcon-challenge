package org.ylp.find.the.odd;

import com.ylp.api.SolveApi;
import com.ylp.model.SolvePost200Response;
import com.ylp.model.SolvePostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindTheOddController implements SolveApi {

  private final FindTheOddService findTheOddService;

  public FindTheOddController(FindTheOddService findTheOddService) {
    this.findTheOddService = findTheOddService;
  }

  @Override
  public ResponseEntity<SolvePost200Response> solvePost(SolvePostRequest solvePostRequest) {
    return ResponseEntity.ok(findTheOddService.findSolution(solvePostRequest));
  }
}
