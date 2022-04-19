using BookingMaster.Data.Model;
using BookingMaster.Data.Response;
using BookingMaster.Exceptions;
using BookingMaster.Services;
using BookingMaster.Utils;
using Microsoft.AspNetCore.Mvc;

namespace BookingMaster.Controllers
{
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
    }
}
