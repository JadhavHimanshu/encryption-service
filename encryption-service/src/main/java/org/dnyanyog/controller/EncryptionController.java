package org.dnyanyog.controller;
@RestController
@RequestMapping 
 @Component
public class EncryptionController {

	  @Autowired private CandidateService candidateService;

	  @PostMapping(
	      path = "/api/v1/auth/candidate",
	      consumes = {"application/json", "application/xml"},
	      produces = {"application/json", "application/xml"})
	  public CandidateResponse addOrUpdateCandidate(@Valid @RequestBody CandidateRequest request)
	      throws Throwable {
	    if (request.getResumeMediaId() == null || request.getResumeMediaId() <= 0) {
	      return candidateService.addOrUpdateCandidate(request);  
	     
	 } else {
		 return candidateService.addOrUpdateCandidate(request);
	   } 
	  }

	  @GetMapping(path = "/api/v1/auth/candidate_search/{resumeMediaId}")
	  public ResponseEntity<CandidateResponse> searchCandidate(
	      @Valid @PathVariable Integer resumeMediaId) {
	    CandidateResponse response = candidateService.findByresumeMediaId(resumeMediaId);
	    return ResponseEntity.ok(response);
	  }

	  @GetMapping(path = "/api/v1/auth/candidate_search_bymobile/{mobile}")
	  public ResponseEntity<CandidateResponse> searchCandidatebymobile(
	      @Valid @PathVariable Integer mobile) {
	    CandidateResponse response = candidateService.findBymobile(mobile);
	    return ResponseEntity.ok(response);
	  }

	  @GetMapping(
	      path = "/api/v1/auth/candidates",
	      produces = {"application/json", "application/xml"})
	  public ResponseEntity<List<CandidateResponse>> getAllCandidates(
	      @RequestBody CandidateRequest request) {
	    List<CandidateResponse> candidates = candidateService.getAllCandidates(request);
	    return ResponseEntity.ok(candidates);
	  }

	  @DeleteMapping(path = "/candidate_delete/{resumeMediaId}")
	  @Transactional
	  public CandidateResponse deleteCandidate(@Valid @PathVariable Integer resumeMediaId) {
	    return candidateService.deleteByresumeMediaId(resumeMediaId);
	  }
	}

}
