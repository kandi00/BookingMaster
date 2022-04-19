using BookingMaster.Data.Response;
using BookingMaster.Exceptions;
using BookingMaster.Repositories;
using BookingMaster.Utils;

namespace BookingMaster.Services
{
    public class AccommodationService : IAccommodationService
    {
        private readonly IAccommodationRepository _accomodationRepository;

        public AccommodationService(IAccommodationRepository accomodationRepository)
        {
            _accomodationRepository = accomodationRepository;
        }

        public async Task<AccommodationsResponse> GetAccomodations()
        {
            try
            {
                AccommodationsResponse response = new AccommodationsResponse();
                response.Accomodations = await _accomodationRepository.GetAccomodations(); ;

                if (response.Accomodations != null)
                {
                    response.Code = 200;
                    response.Message = APISuccessCodes.GET_ACCOMMODATION_SUCCESS;
                }
                return response;
            }
            catch (GetRequestException ex)
            {
                throw new GetException(ex.Message);
            }
        }
    }
}
