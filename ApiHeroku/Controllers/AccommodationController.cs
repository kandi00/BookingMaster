using ApiHeroku.Data.Model;
using ApiHeroku.Data.Response;
using ApiHeroku.Exceptions;
using ApiHeroku.Services;
using ApiHeroku.Utils;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace ApiHeroku.Controllers
{

    //[Authorize]
    [Route("api/[controller]")]
    [ApiController]
    public class AccommodationController : ControllerBase
    {

        private readonly IAccommodationService _accomodationService;

        public AccommodationController(IAccommodationService accomodationService)
        {
            _accomodationService = accomodationService;
        }

        // GET: api/Algorithms
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Accommodation>>> GetAccomodations()
        {
            try
            {
                AccommodationsResponse result = await _accomodationService.GetAccomodations();
                return Ok(result);

            }
            catch (GetException ex)
            {
                AccommodationsResponse errorResponse = new AccommodationsResponse()
                {
                    Code = 400,
                    Message = APIErrorCodes.GET_REQUEST_EXCEPTION_MESSAGE + ex.Message
                };
                return BadRequest(errorResponse);
            }
        }

        [HttpGet, Route("location")]
        public async Task<ActionResult<IEnumerable<Accommodation>>> GetAccomodationsByLocation([FromHeader] string? Location, [FromHeader] string? FromDate, [FromHeader] string? ToDate)
        {
            try
            {
                AccommodationsResponse result = await _accomodationService.GetAccomodationsByLocation(Location, DateOnly.Parse(FromDate), DateOnly.Parse(ToDate));
                return Ok(result);

            }
            catch (GetException ex)
            {
                AccommodationsResponse errorResponse = new AccommodationsResponse()
                {
                    Code = 400,
                    Message = APIErrorCodes.GET_REQUEST_EXCEPTION_MESSAGE + ex.Message
                };
                return BadRequest(errorResponse);
            }
        }

        [HttpGet, Route("name")]
        public async Task<ActionResult<IEnumerable<Accommodation>>> GetAccomodationsByName([FromHeader] string? Name, [FromHeader] string? FromDate, [FromHeader] string? ToDate)
        {
            try
            {
                AccommodationsResponse result = await _accomodationService.GetAccomodationsByName(Name, DateOnly.Parse(FromDate), DateOnly.Parse(ToDate));
                return Ok(result);

            }
            catch (GetException ex)
            {
                AccommodationsResponse errorResponse = new AccommodationsResponse()
                {
                    Code = 400,
                    Message = APIErrorCodes.GET_REQUEST_EXCEPTION_MESSAGE + ex.Message
                };
                return BadRequest(errorResponse);
            }
        }

    }
}
