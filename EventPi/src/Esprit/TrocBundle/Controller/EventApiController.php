<?php

namespace Esprit\TrocBundle\Controller;
use Esprit\TrocBundle\Entity\LigenResrvation;
use Esprit\TrocBundle\Entity\Reservation;
use FOS\RestBundle\Controller\FOSRestController;
use Esprit\TrocBundle\Entity\Event;
use GuzzleHttp\Psr7\Response;
use Symfony\Component\HttpFoundation\Request;
use FOS\RestBundle\Controller\Annotations as Rest;


class EventApiController extends FOSRestController
{

	/**
	 * @Rest\Get("/all")
	 */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();
        $events = $em->getRepository('EspritTrocBundle:Event')->findBy(
        	array(
		        'visibi'=>true
	        )
        );
        return $events;
    }


	/**
	 * @Rest\Get("/annule/{id}")
	 */
	public function annuloneAction($id)
	{

		$em = $this->getDoctrine()->getManager();
		$lreservations = new LigenResrvation();
		$event = new Event();
		$event->setVisibi(true);
		$lreservations = $em->getRepository('EspritTrocBundle:LigenResrvation')->find($id);
		if($lreservations->getQuantite() == 1){
			$id = $lreservations->getEvent()->getId();
			$event = $em->getRepository('EspritTrocBundle:Event')->find($id);
			$n = $event->getNombreplace() + 1;
			$event->setNombreplace($n);
			$em->remove($lreservations);
			$event->setVisibi(true);
			$this->getDoctrine()->getManager()->flush();
		}else {
			$q = $lreservations->getQuantite() - 1;
			$lreservations->setQuantite($q);
			$id = $lreservations->getEvent()->getId();
			$event = $em->getRepository('EspritTrocBundle:Event')->find($id);
			$n = $event->getNombreplace() + 1;
			$event->setVisibi(true);
			$event->setNombreplace($n);
			$this->getDoctrine()->getManager()->flush();
		}
		//var_dump($id);
		return new Response(200);

	}

	/**
	 * @Rest\Get("/annuleall/{id}")
	 */
	public function annulAllAction($id)
	{
		$em = $this->getDoctrine()->getManager();
		$lreservations = new LigenResrvation();
		$lreservations = $em->getRepository('EspritTrocBundle:LigenResrvation')->find($id);
		$q = $lreservations->getQuantite();
		$id = $lreservations->getEvent()->getId();
		$event = $em->getRepository('EspritTrocBundle:Event')->find($id);
		$n = $event->getNombreplace() + $q;
		$event->setNombreplace($n);
		$em->remove($lreservations);
		$event->setVisibi(true);
		$this->getDoctrine()->getManager()->flush();
		return $this->redirectToRoute('reservation_indexuser');

	}



	/**
	 * @Rest\Get("/myEvents/{id}")
	 */
	public function myEvents($id)
	{
		$em = $this->getDoctrine()->getManager();
		$events = $em->getRepository('EspritTrocBundle:Event')->findBy(array(

		));
		return $events;
	}

    public function newAction(Request $request)
    {
        $event = new Event();
        $form = $this->createForm('Esprit\TrocBundle\Form\EventType', $event);
        $form->handleRequest($request);
        $datetime = new \DateTime("now");

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();

           if(( $event->getNombreplace()>0)&&($event->getDate()>$datetime)){
            $event->setVisibi(true);
            $file = $event->getImage();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $fileName);
            $event->setImage($fileName);
            $em->persist($event);
            $em->flush();

            return $this->redirectToRoute('event_index');
        }
        }

        return $this->render('event/new.html.twig', array(
            'event' => $event,
            'form' => $form->createView(),
        ));
    }

    public function showAction(Event $event)
    {
        $deleteForm = $this->createDeleteForm($event);

        return $this->render('event/show.html.twig', array(
            'event' => $event,
            'delete_form' => $deleteForm->createView(),
        ));
    }

	/**
	 * @Rest\Get("/mesReservastions/{id}")
	 */
	public function  myResAction($id)
	{

		$em = $this->getDoctrine()->getManager();
		$user = $em->getRepository('AppBundle:User')->find($id);
		$reservation = $em->getRepository('EspritTrocBundle:Reservation')->findOneBy(array(
			'user'=> $user
		));
		$list= [];
		$reservations = $em->getRepository('EspritTrocBundle:LigenResrvation')->findBy(array(
			'reservation' =>$reservation
		));
		foreach ($reservations as $resv){
			$obj = [
				"id"=>$resv->getId(),
				"userid"=>$user->getId(),
				"nomResto"=> $resv->getEvent()->getNom(),
				"idResto"=> $resv->getEvent()->getId(),
				"quantite"=> $resv->getQuantite()
			];
			array_push($list,$obj);
		}


		return $list;
	}

    public function editAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();
        $datetime = new \DateTime("now");
        $event =  $em->getRepository('EspritTrocBundle:Event')->find($id);
        $image =$event->getImage();
        $editForm = $this->createForm('Esprit\TrocBundle\Form\EventTypeeedit', $event);
        $editForm->handleRequest($request);
        if(( $event->getNombreplace()>0)&&($event->getDate()>$datetime)){
        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $file = $event->getImage();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $fileName);
            $event->setImage($fileName);
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('event_index');
        }}

        return $this->render('event/edit.html.twig', array(
            'event' => $event,
            'edit_form' => $editForm->createView()

        ));
    }



	/**
	 * @Rest\Get("/delete/{id}")
	 */
    public function deleteAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository('EspritTrocBundle:Event')->find($id);
        $em->remove($event);
        $em->flush();
        return new Response("ok");
    }

	/**
	 * @Rest\Get("/get/{id}")
	 */
	public function getActipn($id)
	{
		$em = $this->getDoctrine()->getManager();
		$event = $em->getRepository('EspritTrocBundle:Event')->find($id);
		return $event;
	}


	/**
	 * @Rest\Post("/book/{idu}/{ide}")
	 */
	public function bookAction($idu,$ide) {

		$event = new Event();
		$em = $this->getDoctrine()->getManager();
		$user =$em->getRepository('AppBundle:User')->find($idu);
		$event = $em->getRepository('EspritTrocBundle:Event')->find($ide);
		if($user) {
			$res = $em->getRepository('EspritTrocBundle:Reservation')->findOneBy(array('user' => $user));
			if ($res) {
				$ligres = new LigenResrvation();
				$ligres = $em->getRepository('EspritTrocBundle:LigenResrvation')->findOneBy(array('reservation' => $res , 'event'=>$event));
				if ($ligres) {
					$q = $ligres->getQuantite() + 1;
					$ligres->setQuantite($q);
					$ligres->setReservation($res);
					$n = $event->getNombreplace() - 1;
					$event->setNombreplace($n);
					if($n == 0){
						$event->setVisibi(false);
					}
					$em->flush();
					return new Response(200);
				} else {
					//var_dump($event);

					$ligres = new LigenResrvation();
					$ligres->setEvent($event);
					$ligres->setQuantite(1);
					$ligres->setReservation($res);
					$em->persist($ligres);
					$n = $event->getNombreplace() - 1;
					$event->setNombreplace($n);
					if($n == 0){
						$event->setVisibi(false);
					}
					$this->getDoctrine()->getManager()->flush();
					$em->flush();
				}
			} else {
				$reservation = new Reservation();
				$reservation->setUser($user);
				$em->persist($reservation);
				$em->flush();
				$ligres = new LigenResrvation();
				$ligres->setEvent($event);
				$ligres->setReservation($res);
				$ligres->setQuantite(1);
				$em->persist($ligres);
				$em->flush();
				$n = $event->getNombreplace() - 1 ;
				$event->setNombreplace($n);
				if($n == 0){
					$event->setVisibi(false);
				}
				$em->flush();
				return new Response(200);
			}
		}
		else{
			return new Response(405);
		}
	}

	/**
	 * @Rest\Get("/cancelBook/{id}")
	 */
	public function cancelReservation($id) {
		$em          = $this->getDoctrine()->getManager();
		$reservation = $em->getRepository('RestaurationBundle:Resrevations')->find($id);
		if($reservation != null){
			$em->remove($reservation);
			$em->flush();
			return new Response("canceling");
		}
		return new Response("error canceling ...");
	}


	/**
	 * @Rest\Get("/isReserved/{idu}/{idr}")
	 */
	public function isReservd($idu,$idr) {
		$em          = $this->getDoctrine()->getManager();
		$reservation = $em->getRepository('RestaurationBundle:Resrevations')->findOneBy(array(
			'Id_agence'=>$idu,
			'idResto'=>$idr
		));
		if($reservation != null)
			return new JsonResponse(true);
		return new JsonResponse(false);
	}

	/**
	 * @Rest\Get("/myReservations/{idu}")
	 */
	public function reservationsByUser($idu) {
		$em          = $this->getDoctrine()->getManager();
		$reservations = $em->getRepository('RestaurationBundle:Resrevations')->findBy(array(
			'Id_agence'=>$idu
		));
		if($reservations != null){
			return $reservations;
		}
		return new Response("null");
	}



}
